package org.lekitech.gafalag.jsonmodels;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.SneakyThrows;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Date: 11.11.2021
 * Project: GafalagParser
 * Class: Deserializer
 *
 * @author Enver Eskendarov (envereskendarov@gmail.com)
 * @version 1.0
 */
public class Deserializer {

    private final List<Page> pages;
    @Getter
    private List<Text> tokens;
    private int beginPage;
    private int endPage;

    @SneakyThrows(value = IOException.class)
    public Deserializer(String srcValue) {
        pages = new ObjectMapper().readerFor(new TypeReference<List<Page>>() {
        }).readValue(new File(srcValue));
    }

    @SneakyThrows(value = IOException.class)
    public Deserializer(String srcValue, int beginPage, int endPage) {
        this.beginPage = beginPage;
        this.endPage = endPage;
        pages = new ObjectMapper().readerFor(new TypeReference<List<Page>>() {
        }).readValue(new File(srcValue));
    }

    public Deserializer initTokens() {
        if (beginPage == 0 && endPage == 0) {
            tokens = pages.stream().flatMap(page -> page.getTextBlocks().stream())
                    .collect(Collectors.toList());
        } else {
            tokens = pages.stream().skip(beginPage > 1 ? beginPage - 1 : 0)
                    .limit(endPage > 2 && endPage > beginPage ? endPage + 1 - beginPage : 1)
                    .flatMap(page -> page.getTextBlocks().stream())
                    .collect(Collectors.toList());
        }
        return this;
    }
}
