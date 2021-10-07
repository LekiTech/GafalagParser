package org.lekitech.gafalag.jsonmodels;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Date: 07.10.2021
 * Project: GafalagParser
 * Class: R
 *
 * @author Enver Eskendarov (envereskendarov@gmail.com)
 * @version 1.0
 */
public class R {

    @JsonProperty("T")
    public String value;
    @JsonProperty("S")
    public int s;
    @JsonProperty("TS")
    public List<Double> tS;
}
