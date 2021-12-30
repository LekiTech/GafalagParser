package org.lekitech.gafalag.util;

import java.util.ResourceBundle;

public class Resource {

    private static final ResourceBundle res = ResourceBundle.getBundle("app");
    public static String SOURCE_JSON_PATH = res.getString("source.path");
    public static String TARGET_JSON_PATH = res.getString("target.path");
    public static String DICTIONARY_NAME = res.getString("dictionary.name");
    public static String DICTIONARY_URL = res.getString("dictionary.url");
    public static String SOURCE_LANG_ISO_3 = res.getString("source.lang.iso3");
    public static String TARGET_LANG_ISO_3 = res.getString("target.lang.iso3");
}
