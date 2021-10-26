package org.lekitech.gafalag;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.lekitech.gafalag.dictionarymodels.EntryWord;
import org.lekitech.gafalag.jsonmodels.Page;
import org.lekitech.gafalag.jsonmodels.Text;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Date: 15.10.2021
 * Project: GafalagParser
 * Class: JsonMapper
 *
 * @author Enver Eskendarov (envereskendarov@gmail.com)
 * @version 1.0
 */
public class JsonMapper {

    private final StringBuilder leftPart;
    private final StringBuilder rightPart;
    private final List<EntryWord> dictionary;
    private final ObjectMapper mapper;
    private EntryWord entryWord;
    private List<Page> pages;

    public JsonMapper(String src) {
        leftPart = new StringBuilder();
        rightPart = new StringBuilder();
        dictionary = new ArrayList<>();
        mapper = new ObjectMapper();
        entryWord = new EntryWord();
        try {
            pages = mapper.readerFor(new TypeReference<List<Page>>() {
            }).readValue(new File(src));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void parseJSON() {
        var texts = pages.stream()
                .flatMap(page -> page.getTextOfPage().stream())
                .collect(Collectors.toList());
        for (int i = 0, textItSize = texts.size(); i < textItSize; i++) {
            final Text current = texts.get(i);
            final Text next = i == textItSize - 1 ? current : texts.get(i + 1);
            if (current.isValidZone()) {
                if (current.isHeader()) {
                    leftPart.append(current.value());
                    leftPart.append(current.value().endsWith(" ") ? "" : " ");
                } else {
                    rightPart.append(current.value()).append(" ");
                    if (current.isIdiom() && next.isDefault()) {
                        rightPart.append(": ");
                    }
                }
            }
            if (!current.isNewEntry() && (next.isNewEntry() || i == textItSize - 1)) {
                if (leftPart.length() != 0) {
                    splitHeadAndInflection(checkPunc(leftPart.toString()));
                    splitDefinitions(checkPunc(rightPart.toString()));
                    addEntry(entryWord);
                    leftPart.setLength(0);
                    rightPart.setLength(0);
                }
            }
        }
    }

    public void writeJSON(String target) {
        parseJSON();
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValues(new File(target)).write(dictionary);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printJSON() {
        parseJSON();
        try {
            System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(dictionary));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    private void splitHeadAndInflection(String value) {
        if (value.matches(".*\\(.*\\).*")) {
            final String[] s = value.split("[()]");
            entryWord.setHead(s[0].trim().toUpperCase());
            entryWord.setInflection(String.format("(%s)", s[1].trim()));
        } else {
            entryWord.setHead(value.trim().toUpperCase());
        }
    }

    private void splitDefinitions(String rightPart) {
        var defs = Arrays
                .stream(rightPart.trim().split("[1-9]+[.)]|;"))
                .filter(s -> !s.isBlank())
                .map(String::trim)
                .collect(Collectors.toList());
        entryWord.setDefinitions(defs);
    }

    private void addEntry(final EntryWord entry) {
        dictionary.add(entry);
        entryWord = new EntryWord();
    }

    private String checkPunc(String value) {
        return value
                .replace(" ,", ",")
                .replace(" .", ".")
                .replace(" !", "!")
                .replace(" ?", "?")
                .replace(" ;", ";")
                .replace(" :", ":")
                .replace("( ", "(")
                .replace(" )", ")")
                .replace("  ", " ")
                .replace(" I ", "I")
                .replace("- ", "-")
                .replace(" -", "-");
    }
}
