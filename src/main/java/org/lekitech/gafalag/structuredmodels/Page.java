package org.lekitech.gafalag.structuredmodels;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

/**
 * Date: 15.10.2021
 * Project: GafalagParser
 * Class: Page
 * <p>
 * <a href='https://github.com/modesty/pdf2json'>Actually Info</a>
 *
 * @author Enver Eskendarov (envereskendarov@gmail.com)
 * @version 1.0
 */
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(value = {"Height", "HLines", "VLines", "Fills", "Fields", "Boxsets"})
public class Page {

    @JsonProperty("Texts")
    private List<Text> textData;
}
