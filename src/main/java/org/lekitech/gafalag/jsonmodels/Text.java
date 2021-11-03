package org.lekitech.gafalag.jsonmodels;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

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
@JsonIgnoreProperties(value = {"A", "sw", "w", "clr"})
public class Text {

    @JsonProperty("oc")
    private String originColorHex;
    private double x, y;
    @JsonProperty("R")
    private List<Run> run; // an array of text run
}
