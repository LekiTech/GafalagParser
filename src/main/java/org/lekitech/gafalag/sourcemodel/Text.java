package org.lekitech.gafalag.sourcemodel;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.lekitech.gafalag.sourcemodel.exception.UnsupportedColorException;

import java.util.List;

/**
 * Date: 15.10.2021
 * Project: GafalagParser
 * Class: Text - an array of text blocks with position, actual text and styling information
 *
 * @author Enver Eskendarov (envereskendarov@gmail.com)
 * @version 1.0
 */
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Text {

    private int clr;
    private double x, y, w, sw;
    @JsonProperty("oc")
    private String originalColor;
    @JsonProperty("A")
    private String alignment;
    @JsonProperty("R")
    private List<Run> run; // an array of text run

    public boolean colorIs(Color color) {
        return getColor().equals(color);
    }

    public boolean colorIs(Text text) {
        return text != null && getColor() == text.getColor();
    }

    public Color getColor() {
        try {
            return originalColor != null ? Color.of(originalColor) : Color.of(clr);
        } catch (UnsupportedColorException e) {
            e.printStackTrace();
            return Color.BLACK;
        }
    }

    public String getText() {
        return run.get(0).getTextBlock();
    }

    /**
     * @return sw - space width of the font
     */
    public double getFontSw() {
        return sw;
    }

    public boolean swEquals(Text other) {
        return other != null && this.sw == other.sw;
    }

    public int getFontFaceId() {
        return run.get(0).getTextStyle().get(0).intValue();
    }

    public int getFontSize() {
        return run.get(0).getTextStyle().get(1).intValue();
    }

    public boolean isBold() {
        return run.get(0).getTextStyle().get(2).intValue() == 1;
    }

    public boolean isItalic() {
        return run.get(0).getTextStyle().get(3).intValue() == 1;
    }
}
