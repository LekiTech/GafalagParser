package org.lekitech.gafalag.jsonmodels;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

/**
 * Date: 15.10.2021
 * Project: GafalagParser
 * <p>
 * Class: Page - JSON Model from PDF page
 * <p>
 * <a href='https://github.com/modesty/pdf2json'>PDF Parser to JSON</a>
 *
 * @author Enver Eskendarov (envereskendarov@gmail.com)
 * @version 1.0
 */
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(value = {"Height", "HLines", "VLines", "Fills", "Fields", "Boxsets"})
public class Page {

    @JsonProperty("Texts")
    private List<Text> textOfPage;
}
