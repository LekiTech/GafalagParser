package org.lekitech.gafalag.sourcemodel;

import lombok.RequiredArgsConstructor;
import org.lekitech.gafalag.sourcemodel.exception.UnsupportedColorException;

import java.util.Arrays;
import java.util.Objects;

/**
 * Date: 17.10.2021
 * Project: GafalagParser
 * Enum: Color
 *
 * @author Enver Eskendarov (envereskendarov@gmail.com)
 * @version 1.0
 */
@RequiredArgsConstructor
public enum Color {

    // color dictionary: https://github.com/modesty/pdf2json
    BLACK("#000000"),
    BLUE("#003365"),
    GREEN("#007f00"),
    BROWN("#261300"),
    WHITE("#ffffff"),
    DARK_GRAY1("#4c4c4c"),
    DARK_GRAY2("#808080"),
    DARK_GRAY3("#999999"),
    DARK_GRAY4("#969696"),
    LIGHT_GRAY1("#c0c0c0"),
    LIGHT_GRAY2("#cccccc"),
    LIGHT_GRAY3("#e5e5e5"),
    LIGHT_GRAY4("#f2f2f2"),
    DARK_LIME_GREEN("#008000"),
    PURE_LIME_GREEN("#00ff00"),
    PALE_GREEN("#bfffa0"),
    VIVID_YELLOW("#ffd629"),
    LIGHT_PINK("#ff99cc"),
    DARK_BLUE1("#004080"),
    SOFT_BLUE1("#9fc0e1"),
    DARK_BLUE2("#5580ff"),
    SOFT_BLUE2("#a9c9fa"),
    PURE_PINK("#ff0080"),
    DARK_MAGENTA("#800080"),
    PALE_MAGENTA("#ffbfff"),
    VIVID_ORANGE("#e45b21"),
    PALE_RED("#ffbfaa"),
    DARK_CYAN1("#008080"),
    PURE_RED("#ff0000"),
    SOFT_ORANGE("#fdc59f"),
    OLIVE("#808000"),
    STRONG_YELLOW("#bfbf00"),
    LIGHT_BROWN("#824100"),
    DARK_CYAN("#007256");

    final static String[] colors = new String[]{
            "#000000",        // 0
            "#ffffff",        // 1
            "#4c4c4c",        // 2
            "#808080",        // 3
            "#999999",        // 4
            "#c0c0c0",        // 5
            "#cccccc",        // 6
            "#e5e5e5",        // 7
            "#f2f2f2",        // 8
            "#008000",        // 9
            "#00ff00",        // 10
            "#bfffa0",        // 11
            "#ffd629",        // 12
            "#ff99cc",        // 13
            "#004080",        // 14
            "#9fc0e1",        // 15
            "#5580ff",        // 16
            "#a9c9fa",        // 17
            "#ff0080",        // 18
            "#800080",        // 19
            "#ffbfff",        // 20
            "#e45b21",        // 21
            "#ffbfaa",        // 22
            "#008080",        // 23
            "#ff0000",        // 24
            "#fdc59f",        // 25
            "#808000",        // 26
            "#bfbf00",        // 27
            "#824100",        // 28
            "#007256",        // 29
            "#008000",        // 30
            "#000080",        // Last + 1
            "#008080",        // Last + 2
            "#800080",        // Last + 3
            "#ff0000",        // Last + 4
            "#0000ff",        // Last + 5
            "#008000",        // Last + 6
            "#000000"         // Last + 7
    };

    private final String hex;

    public static Color of(int color) throws UnsupportedColorException {
        return Arrays.stream(values())
                .filter(clr -> Objects.equals(clr.hex, colors[color]))
                .findFirst()
                .orElseThrow(() -> new UnsupportedColorException("Unknown color: " + color));
    }

    public static Color of(String color) throws UnsupportedColorException {
        return Arrays.stream(values())
                .filter(clr -> Objects.equals(clr.hex, color))
                .findFirst()
                .orElseThrow(() -> new UnsupportedColorException("Unknown color: " + color));
    }
}
