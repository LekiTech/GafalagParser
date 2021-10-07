package org.lekitech.gafalag.jsonmodels;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Date: 07.10.2021
 * Project: GafalagParser
 * Class: Root
 *
 * @author Enver Eskendarov (envereskendarov@gmail.com)
 * @version 1.0
 */
public class Root {

    @JsonProperty("Height")
    public double height;
    @JsonProperty("HLines")
    public List<Object> hLines;
    @JsonProperty("VLines")
    public List<Object> vLines;
    @JsonProperty("Fills")
    public List<Fill> fills;
    @JsonProperty("Texts")
    public List<Text> texts;
    @JsonProperty("Fields")
    public List<Object> fields;
    @JsonProperty("Boxsets")
    public List<Object> boxsets;
}

