package org.lekitech.gafalag.jsonmodels;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.LinkedList;

import static org.lekitech.gafalag.jsonmodels.Color.BLACK;
import static org.lekitech.gafalag.jsonmodels.Color.BLUE;

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

    @JsonProperty("oc")
    private String color;
    private double x, y, w, sw, clr;
    @JsonProperty("R")
    private LinkedList<R> r;

    public boolean isHeader() {
        return getColor().equals(BLUE) && isBold();
    }

    public boolean isIdiom() {
        return getColor().equals(BLACK) && isBold();
    }

    public boolean isDefault() {
        return getFontSize() == 13.02 && !isBold();
    }

    public boolean isNewEntry() {
        return getX() == 2.337 && isHeader();
    }

    /**
     * @return Возвращает область страницы без верхнего колонтитула.
     */
    public boolean isValidZone() {
        return getY() > 1.012;
    }

    public String value() {
        return isValidZone() ? r.get(0).getText() : null;
    }

    public Color getColor() {
        return Color.ofHex(color);
    }

    public double getFontSize() {
        return r.get(0).getFontType().get(1);
    }

    public boolean isBold() {
        return r.get(0).getFontType().get(2) == 1;
    }
}
