package org.lekitech.gafalag;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.SneakyThrows;
import org.lekitech.gafalag.dictionarymodels.Article;
import org.lekitech.gafalag.jsonmodels.Page;
import org.lekitech.gafalag.jsonmodels.Token;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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

    public Mapper(String srcFile) {
        this.srcFile = srcFile;
    }

    @SneakyThrows(value = IOException.class)
    public Mapper init() {
        final List<Page> pages = readerFor(new TypeReference<List<Page>>() {
        }).readValue(new File(srcFile));
        final List<Token> tokens = pages.stream()
                .flatMap(page -> page.getTextBlocks().stream())
                .map(Token::new).filter(Token::isValid).collect(Collectors.toList());
        for (int i = 0, size = tokens.size(); i < size; i++) {
            final Token current = tokens.get(i);
            final Token previous = (i == 0 || current.isStartNewArticle()) ? null : tokens.get(i - 1);
            final Token next = ((i == size - 1) || tokens.get(i + 1).isStartNewArticle()) ? null : tokens.get(i + 1);
            switch (current.getColor()) {
                case BLUE -> setExprAndInfSplit(current);
                case GREEN -> setTagsAndAppend(previous, current, next, "<", ">");
                case BLACK -> setTagsAndAppend(previous, current, next, "{", "}");
                case BROWN -> setTagsAndAppend(previous, current, next, "", "");
            }
            if (next == null) {
                dictionary.add(setNewArticle());
                exp.setLength(0);
                inf.setLength(0);
                def.setLength(0);
            }
        }
        return this;
    }

    private void setExprAndInfSplit(Token token) {
        if (token.getText().contains("(")) {
            var split = token.getText().split("(\\()", 2);
            exp.append(split[0]);
            inf.append("(").append(split[1]);
        } else if (inf.length() == 0) {
            exp.append(token.getText());
        } else {
            inf.append(token.getText()).append(" ");
        }
    }

    private Article setNewArticle() {
        return new Article(
                exp.toString().trim(),
                inf.length() != 0 ? checkPartsOfSpeech(inf.toString()) : null,
                Stream.of(checkPartsOfSpeech(def.toString()).split("(?=\\d\\.)")
                ).filter(s -> !s.isEmpty() && !s.isBlank()).map(String::trim).collect(Collectors.toList())
        );
    }

    private void setTagsAndAppend(Token previous, Token current, Token next, String firstTag, String lastTag) {
        if (previous == null || current.getColor() != previous.getColor()) {
            def.append(firstTag);
        }
        def.append(current.getText());
        if (!current.getText().endsWith(" ")) {
            def.append(" ");
        }
        if (next == null || current.getColor() != next.getColor()) {
            def.append(lastTag).append(" ");
        }
    }

    private String checkPartsOfSpeech(String text) {
        return text
                .replaceAll("\\s?([\\-I/])\\s?", "$1")
                .replaceAll("\\s?([!,.:;?])", "$1")
                .replaceAll("([<{(])\\s+", "$1")
                .replaceAll("\\s+([)}>])", "$1 ")
                .replaceAll("([)}>])\\s([;.,:])", "$1$2")
                .replaceAll("([\\-I])\\s+", "$1")
                .replaceAll("\\s?Ø\\s?", "")
                .replaceAll(",-", ", -")
                .replaceAll("\\s+", " ")
                .trim();
    }
}
