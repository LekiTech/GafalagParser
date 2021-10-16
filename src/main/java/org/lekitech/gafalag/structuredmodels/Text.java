package org.lekitech.gafalag.structuredmodels;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

/**
 * Date: 15.10.2021
 * Project: GafalagParser
 * Class: Text
 *
 * @author Enver Eskendarov (envereskendarov@gmail.com)
 * @version 1.0
 */
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(value = {"A"})
public class Text {

    private final double PAGE_NUMBER_POS = 1.012;
    private final double FIRST_WORD_POS = 2.337;
    private final String COLOR_OF_THE_FIRST_WORD = "#003365";
    private final String COLOR_OF_THE_EXPLANATION = "#007f00";
    private final String COLOR_DEFAULT = "#261300"; // like black color #000000
    private final Predicate<R> SKIP_ELEMENTS_ATR = r -> (r.getStyle() == 3 && getFontSize() == 12) || (this.getY() == PAGE_NUMBER_POS);
    private final Predicate<R> WORD_OF_LEZGI_EXAMPLE = r -> getFontStyle() == 2 && getFontSize() == 14 && isBold() && getColor().equals(COLOR_DEFAULT);
    private final Predicate<R> WORD_OF_EXPLANATION = r -> getFontStyle() == 2 && getFontSize() == 13 && getColor().equals(COLOR_OF_THE_EXPLANATION);
    private final Predicate<R> DEFAULT_TEXT_RUS = r -> getFontStyle() == 2 && getFontSize() == 13 && getColor().equals(COLOR_DEFAULT);
    private final Predicate<R> WORD_FOR_TRANSLATE = r -> getFontStyle() == 2 && getFontSize() == 14 && isBold() && getColor().equals(COLOR_OF_THE_FIRST_WORD);

    private String oc;
    private double x, y, w, sw, clr;
    @JsonProperty("R")
    private List<R> r;

    public boolean isNewWord() {
        return x == FIRST_WORD_POS && getColor().equals(COLOR_OF_THE_FIRST_WORD);
    }

    public String get() {
        return SKIP_ELEMENTS_ATR.test(r.get(0)) ? "" : r.get(0).getText();
    }

    public String getColor() {
        return Optional.of(oc).orElse("#000000");
    }

    private int getFontStyle() {
        return r.get(0).getStyle();
    }

    private int getFontSize() {
        return r.get(0).getFontType().get(1).intValue();
    }

    public boolean isBold() {
        return r.get(0).getFontType().get(2).intValue() == 1;
    }

    public boolean isItalic() {
        return r.get(0).getFontType().get(3).intValue() == 1;
    }
}
