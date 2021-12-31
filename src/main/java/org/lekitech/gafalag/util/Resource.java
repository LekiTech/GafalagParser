package org.lekitech.gafalag.util;

import lombok.extern.java.Log;

import java.util.ResourceBundle;

@Log
public class Resource {

    private static final ResourceBundle res = ResourceBundle.getBundle("app");
    public static final String SOURCE_JSON_PATH = res.getString("source.path");
    public static final String TARGET_JSON_PATH = res.getString("target.path");
    public static final String DICTIONARY_NAME = res.getString("dictionary.name");
    public static final String DICTIONARY_URL = res.getString("dictionary.url");
    public static final String EXPRESSION_LANGUAGE_ISO_3 = res.getString("expression.language.iso3");
    public static final String DEFINITION_LANGUAGE_ISO_3 = res.getString("definition.language.iso3");
    public static int START_PAGE;
    public static int END_PAGE;

    static {
        try {
            START_PAGE = Integer.parseInt(res.getString("parse.pages").split("-")[0]);
            END_PAGE = Integer.parseInt(res.getString("parse.pages").split("-")[1]);
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            log.warning("Input page number like: startPage-endPage");
        }
    }
}
