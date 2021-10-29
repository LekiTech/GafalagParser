package org.lekitech.gafalag;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.lekitech.gafalag.dictionarymodels.Article;
import org.lekitech.gafalag.jsonmodels.Page;
import org.lekitech.gafalag.jsonmodels.Text;
import org.lekitech.gafalag.jsonmodels.TextProperty;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * Date: 15.10.2021
 * Project: GafalagParser
 * Class: DictionaryMapper
 *
 * @author Enver Eskendarov (envereskendarov@gmail.com)
 * @version 1.0
 */
public class DictionaryMapper {

    private final StringBuilder leftPart;
    private final StringBuilder rightPart;
    private final List<Article> articles;
    private final ObjectMapper mapper;
    private final TextProperty check;
    private Article article;
    private List<Page> pages;

    public DictionaryMapper(String src) {
        leftPart = new StringBuilder();
        rightPart = new StringBuilder();
        articles = new ArrayList<>();
        mapper = new ObjectMapper();
        check = new TextProperty();
        article = new Article();
        try {
            pages = mapper.readerFor(new TypeReference<List<Page>>() {
            }).readValue(new File(src));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void parseJSON() {
        final List<Text> texts = pages.stream()
                .limit(4)
                .flatMap(page -> page.getTextBlocks().stream())
                .filter(check::nonIgnoreText)
                .collect(Collectors.toList());
        for (int i = 0, size = texts.size(); i < size; i++) {
            final Text current = texts.get(i);
            final Text next = (i == size - 1) ? current : texts.get(i + 1);
            final String textBlock = current.getRun().get(0).getTextBlock();
            if (check.isHeader(current)) {
                if (!textBlock.endsWith(" ")) {
                    leftPart.append(textBlock).append(" ");
                } else {
                    leftPart.append(textBlock);
                }
            } else {
                rightPart.append(textBlock).append(" ");
                if (!check.isIdiom(current) && check.isIdiom(next)) {
                    rightPart.append("<");
                } else if (check.isIdiom(current) && !check.isIdiom(next)) {
                    rightPart.append(">");
                }
            }
            if (!check.isNewArticle(current) && (check.isNewArticle(next) || i == size - 1)) {
                if (leftPart.length() != 0) {
                    splitHeadAndForms(leftPart.toString());
                    splitDefinitions(rightPart.toString());
                    addArticle(article);
                    leftPart.setLength(0);
                    rightPart.setLength(0);
                }
            }
        }
    }

    public void writeJSON(String target) {
        parseJSON();
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValues(new File(target)).write(articles);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printJSON() {
        parseJSON();
        try {
            System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(articles));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    private void splitHeadAndForms(String value) {
        final String[] s = value.split("[()]");
        article.setHead(checkPunc(s[0].trim()));
        if (s.length > 1) {
            article.setForms(checkPunc(String.format("(%s)", s[1].trim())));
        }
    }

    private void splitDefinitions(String rightPart) {
        var objects = Arrays.stream(rightPart.split("[0-9]+\\."))
                .map(String::trim)
                .filter(s -> !s.isBlank())
                .map(s -> Arrays.stream(s.split("[0-9]+\\)")).collect(Collectors.toList()));
        addArticle(article);
    }

    private void addArticle(final Article article) {
        articles.add(article);
        this.article = new Article();
    }

    private String checkPunc(String value) {
        return value
                .replace("- ", "-")
                .replace(" -", "-")
                .replace(" ,", ",")
                .replace(",-", ", -")
                .replace(" .", ".")
                .replace(" !", "!")
                .replace(" ?", "?")
                .replace(" ;", ";")
                .replace(" :", ":")
                .replace("( ", "(")
                .replace(" )", ")")
                .replace("< ", " <")
                .replace(" >", "> ")
                .replace("  ", " ")
                .replace("  ", " ")
                .replace(" I ", "I")
                .replace(" /", "/");
    }
}
