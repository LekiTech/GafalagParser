package org.lekitech.gafalag.jsonmodels;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Date: 07.10.2021
 * Project: GafalagParser
 * Class: Text
 *
 * @author Enver Eskendarov (envereskendarov@gmail.com)
 * @version 1.0
 */
public class Text {

    @JsonProperty("oc")
    public String dictTextColor;
    public double x;
    public double y;
    public double w;
    public double sw;
    public int clr;
    @JsonProperty("A")
    public String a;
    @JsonProperty("R")
    public List<R> r;
}
