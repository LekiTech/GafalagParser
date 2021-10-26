package org.lekitech.gafalag.jsonmodels;

import java.util.Arrays;

/**
 * Date: 17.10.2021
 * Project: GafalagParser
 * Enum: Color
 *
 * @author Enver Eskendarov (envereskendarov@gmail.com)
 * @version 1.0
 */
public enum Color {


    BLUE("#003365"),
    GREEN("#007f00"),
    BROWN("#261300"),
    BLACK("#000000");

    private final String hex;

    Color(String hex) {
        this.hex = hex;
    }

    public static Color ofHex(String hex) {
        return Arrays.stream(values())
                .filter(value -> value.hex.equals(hex))
                .findFirst()
                .orElse(BLACK);
    }
}
