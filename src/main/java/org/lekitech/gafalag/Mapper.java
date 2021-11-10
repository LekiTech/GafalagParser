package org.lekitech.gafalag;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.SneakyThrows;
import org.lekitech.gafalag.dictionarymodels.Article;
import org.lekitech.gafalag.jsonmodels.Color;
import org.lekitech.gafalag.jsonmodels.Page;
import org.lekitech.gafalag.jsonmodels.Text;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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

public class Mapper extends ObjectMapper {

    private final String srcFile;
    @Getter
    private final List<Article> dictionary = new ArrayList<>();
    final StringBuilder exp = new StringBuilder();
    final StringBuilder inf = new StringBuilder();
    final StringBuilder def = new StringBuilder();

    // valid page area
    final Predicate<Text> valid = text -> text.getY() > 1.012d;
    final Predicate<Text> startArticle = text -> text.getX() < 2.65 && text.colorIs(Color.BLUE);

    public Mapper(String srcFile) {
        this.srcFile = srcFile;
    }

    @SneakyThrows(value = IOException.class)
    public Mapper init() {
        final List<Page> pages = readerFor(new TypeReference<List<Page>>() {
        }).readValue(new File(srcFile));
        final List<Text> tokens = pages.stream()
                .flatMap(page -> page.getTextBlocks().stream())
                .filter(valid)
                .collect(Collectors.toList());
        FileWriter writer = new FileWriter("res.txt");
        for (int i = 0, size = tokens.size(); i < size; i++) {

            final Text current = tokens.get(i);
            final Text previous = (i == 0 || startArticle.test(current)) ? null : tokens.get(i - 1);
            final Text next = ((i == size - 1) || startArticle.test(tokens.get(i + 1))) ? null : tokens.get(i + 1);
            if (current.getW() == 0.483) {
                writer.write(current.getText());
            }
            switch (current.getColor()) {
                case BLUE -> checkSpaceAndAddHead(current, next);
                case GREEN -> setTagsAndSpaceAndAppend(previous, current, next, "<", ">");
                case BLACK -> setTagsAndSpaceAndAppend(previous, current, next, "{", "}");
                case BROWN -> setTagsAndSpaceAndAppend(previous, current, next, "", "");
            }
            addArticleIfNotExist(next);
        }
        writer.flush();
        return this;
    }

    private void checkSpaceAndAddHead(Text current, Text next) {
        if (current.getText().matches(".*[кптцч]") && next != null && next.getText().startsWith("I")
                || current.getText().startsWith("I") && next != null && !next.getText().startsWith("I")
                || current.getText().contains("-") || next != null && next.getText().contains("-")
                || current.getText().contains("[") || next != null && next.getText().contains("]")
                || next != null && next.getText().contains(".") || next != null && next.getText().contains(",")
        ) {
            exp.append(current.getText());
        } else {
            exp.append(current.getText()).append(" ");
        }
    }

    private void expSplit() {
        var expInf = exp.toString().split("[()]");
        if (expInf.length > 1) {
            exp.setLength(0);
            exp.append(expInf[0]);
            inf.append(expInf[1]);
        }
        var expDef = exp.toString().split(":");
        if (expDef.length > 1) {
            exp.setLength(0);
            exp.append(expDef[0]);
            def.insert(0, "{" + expDef[1].trim() + "} ");
        }
    }

    private void addArticleIfNotExist(Text next) {
        if (next == null) {
            expSplit();
            dictionary.add(new Article(
                    (exp.toString().replaceAll("\\s{2,}", " ")).trim(),
                    inf.length() != 0 ? checkPartsOfSpeechAndPunc(inf.toString()).trim() : null,
                    Stream.of(checkPartsOfSpeechAndPunc(def.toString()).split("(?=\\d\\.)")
                    ).filter(s -> !s.isEmpty() && !s.isBlank()).map(String::trim).collect(Collectors.toList())
            ));
            exp.setLength(0);
            inf.setLength(0);
            def.setLength(0);
        }
    }

    private void setTagsAndSpaceAndAppend(Text previous, Text current, Text next, String firstTag, String lastTag) {
        if (previous == null || current.getColor() != previous.getColor()) {
            def.append(" ").append(firstTag);
        }
        def.append(current.getText());
        if (!current.getText().endsWith(" ") && current.swEquals(next) && current.colorIs(next)) {
            def.append(" ");
        }
        if (next == null || current.getColor() != next.getColor()) {
            def.append(lastTag);
        }
    }

    private String checkPartsOfSpeechAndPunc(String text) {
        return text
                .replaceAll("([({<\\[])\\s", "$1")
                .replaceAll("\\s([,:;.>})\\]])", "$1")
                .replaceAll("([\\p{IsCyrillic}\\w[^\\s{<]])(\\()", "$1 $2")
                .replaceAll("(\\.)([\\p{IsCyrillic}\\w[^\\s}>)]])", "$1 $2")
                .replaceAll("\\s{2,}", " ")
                .replaceAll("(,)(-)", "$1 $2")
                .replaceAll("Ø", "♦")
                .trim();
    }
}
