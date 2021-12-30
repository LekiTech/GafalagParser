package org.lekitech.gafalag.dictionary.mapper;

import lombok.*;
import org.lekitech.gafalag.dictionary.model.Article;
import org.lekitech.gafalag.dictionary.model.Dictionary;
import org.lekitech.gafalag.sourcemodel.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Date: 3.11.2021
 * Project: GafalagParser
 * Class: Mapper
 *
 * @author Enver Eskendarov (envereskendarov@gmail.com)
 * @version 1.0
 */
@RequiredArgsConstructor
public class Mapper {

    private final @NonNull MapperHandler handler;
    @Getter
    private Dictionary dictionary;
    private final List<Article> articles = new ArrayList<>();

    public Mapper init() {
        final List<Text> tokens = handler.pagesToTextTokens();
        StringBuilder head = new StringBuilder();
        StringBuilder def = new StringBuilder();
        for (int i = 0, size = tokens.size(); i < size; i++) {
            final Text current = tokens.get(i);
            final Text previous = (i == 0 || handler.getStartArticle().test(current)) ? null : tokens.get(i - 1);
            final Text next = ((i == size - 1) || handler.getStartArticle().test(tokens.get(i + 1))) ? null : tokens.get(i + 1);
            switch (current.getColor()) {
                case BLUE -> head.append(setSpacesInHead(current, next));
                case GREEN -> def.append(setTagsAndSpaces(previous, current, next, "<", ">"));
                case BLACK -> def.append(setTagsAndSpaces(previous, current, next, "{", "}"));
                case BROWN -> def.append(setTagsAndSpaces(previous, current, next, "", ""));
            }
            if (next == null) {
                articles.add(getArticleOf(head, def));
                head = new StringBuilder();
                def = new StringBuilder();
            }
        }
        dictionary = new Dictionary(articles);
        return this;
    }

    private String setSpacesInHead(Text current, Text next) {
        final StringBuilder head = new StringBuilder();
        // special letters: кI, КI, пI, ПI, тI, ТI, цI, ЦI, чI, ЧI
        final boolean isSpecialLetter = current.getText().matches(".*[кптцч]") && next != null && next.getText().startsWith("I");
        final boolean specialLetterPart = current.getText().startsWith("I") && next != null && !next.getText().startsWith("I");
        final boolean isCharAfterNotSpace = current.getText().matches(".*[\\[\\-/á].*");
        final boolean isCharBeforeNotSpace = next != null && next.getText().matches("[.,\\-\\]/á].*");
        if (isSpecialLetter || specialLetterPart || isCharAfterNotSpace || isCharBeforeNotSpace) {
            head.append(current.getText());
        } else {
            head.append(current.getText()).append(" ");
        }
        return head.toString();
    }

    private String setTagsAndSpaces(Text previous, Text current, Text next, String firstTag, String lastTag) {
        final StringBuilder str = new StringBuilder();
        final Predicate<Text> addTags = text -> text == null || !current.colorIs(text);
        if (addTags.test(previous)) {
            str.append(" ").append(firstTag);
        }
        str.append(current.getText());
        if (!current.getText().endsWith(" ") && current.swEquals(next) && current.colorIs(next)) {
            str.append(" ");
        }
        if (addTags.test(next)) {
            str.append(lastTag);
        }
        return str.toString();
    }

    private Article getArticleOf(StringBuilder head, StringBuilder defs) {
        final String[] split = split(head, defs);
        final String expression = split[0];
        final String inflection = split[1];
        final String definition = split[2];
        final List<String> definitions = Stream.of(definition.split("(?=\\d\\.)"))
                .filter(s -> !s.isEmpty() && !s.isBlank())
                .map(this::checkPartsOfSpeechAndPunc)
                .collect(Collectors.toList());
        return new Article(expression, inflection, definitions);
    }

    private String[] split(StringBuilder head, StringBuilder definition) {
        head = replaceExcept(head);
        String expression = checkPartsOfSpeechAndPunc(head.toString());
        String inflection = null;
        final String[] expInf = head.toString().split("[()]");
        if (expInf.length == 2) {
            expression = checkPartsOfSpeechAndPunc(expInf[0]);
            inflection = checkPartsOfSpeechAndPunc(expInf[1]);
        } else if (expInf.length == 3) {
            expression = checkPartsOfSpeechAndPunc(expInf[0]);
            inflection = checkPartsOfSpeechAndPunc(expInf[1]);
            if (!expInf[2].isBlank() && (expInf[2].contains(";") || expInf[2].contains(":"))) {
                definition.insert(0, "{" + expInf[2].trim().split("[:;]")[1].trim() + "} ");
            }
        } else {
            final String[] expDef = head.toString().split("[;:]");
            if (expDef.length > 1) {
                expression = checkPartsOfSpeechAndPunc(expDef[0]);
                definition.insert(0, "{" + expDef[1].trim() + "} ");
            }
        }
        return new String[]{expression, inflection, definition.toString()};
    }

    private StringBuilder replaceExcept(StringBuilder head) {
        if (head.toString().startsWith("элиф ( а )")) {
            return new StringBuilder("элифарун (-из, -на, -а)");
        }
        if (head.toString().startsWith("къарбу :")) {
            return new StringBuilder("къарбу : къарбудин тар");
        }
        if (head.toString().startsWith("як  ( якIу")) {
            return new StringBuilder("як (якIу, якIа, якар)");
        }
        return head;
    }

    private String checkPartsOfSpeechAndPunc(String text) {
        return text
                .replaceAll("([({<\\[/])\\s", "$1")
                .replaceAll("\\s([,:;.>})\\]])", "$1")
                .replaceAll("([\\p{IsCyrillic}\\w[^\\s{<]])(\\()", "$1 $2")
                .replaceAll("(\\.)([\\p{IsCyrillic}\\w[^\\s}>)]])", "$1 $2")
                .replaceAll("\\s{2,}", " ")
                .replaceAll(",-", ", -")
                .replaceAll("Ø", "♦")
                .trim();
    }
}
