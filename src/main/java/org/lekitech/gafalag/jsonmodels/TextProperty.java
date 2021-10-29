package org.lekitech.gafalag.jsonmodels;

import java.util.ArrayList;
import java.util.List;

/**
 * Date: 28.10.2021
 * Project: GafalagParser
 * Class: TextProperty
 *
 * @author Enver Eskendarov (envereskendarov@gmail.com)
 * @version 1.0
 */
public class TextProperty {

    public final static List<Double> BOLD = new ArrayList<>(List.of(2d, 14.02d, 1d, 0d));
    public final static List<Double> PLAIN = new ArrayList<>(List.of(2d, 13.02d, 0d, 0d));
    public final static List<Double> PAGE_HEADER = new ArrayList<>(List.of(0d, 12d, 0d, 0d));
    public final static List<Double> PAGE_NUM = new ArrayList<>(List.of(0d, 13.98d, 0d, 0d));

    public boolean isNonWordChar(Text text) {
        final List<Double> textStyle = getStyleOf(text);
        return textStyle.get(0) == 0 && textStyle.get(3) == 0;
    }

    public boolean isHeader(Text text) {
        return (getStyleOf(text).equals(BOLD) || isNonWordChar(text))
                && Color.ofText(text).equals(Color.BLUE);
    }

    public boolean isNewArticle(Text text) {
        return text.getX() == 2.337 && isHeader(text);
    }

    public boolean isIllustration(Text text) {
        return (getStyleOf(text).equals(PLAIN) || (isNonWordChar(text))
                && Color.ofText(text).equals(Color.GREEN));
    }

    public boolean nonIgnoreText(Text text) {
        final List<Double> textStyle = getStyleOf(text);
        return !(textStyle.equals(PAGE_HEADER) || textStyle.equals(PAGE_NUM));
    }

    public boolean isDefault(Text text) {
        return (getStyleOf(text).equals(PLAIN) || isNonWordChar(text))
                && Color.ofText(text).equals(Color.BROWN);
    }

    public boolean isIdiom(Text text) {
        return (getStyleOf(text).equals(BOLD) || (isNonWordChar(text)))
                && Color.ofText(text).equals(Color.BLACK);
    }

    private List<Double> getStyleOf(Text text) {
        return text.getRun().get(0).getTextStyle();
    }
}
