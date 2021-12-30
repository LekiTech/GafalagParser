package org.lekitech.gafalag.jsonmodels;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * Date: 15.10.2021
 * Project: GafalagParser
 * <p>
 * Class: PdfPage - JSON Model from PDF page
 * <p>
 * <a href='https://github.com/modesty/pdf2json'>PDF Parser to JSON</a>
 *
 * @author Enver Eskendarov (envereskendarov@gmail.com)
 * @version 1.0
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PdfPage {

    @JsonProperty("Height")
    private double height;
    @JsonProperty("HLines")
    private List<Object> hLines;
    @JsonProperty("VLines")
    private List<Object> vLines;
    @JsonProperty("Fills")
    private List<Fill> fills;
    @JsonProperty("Texts")
    private List<Text> textBlocks;
    @JsonProperty("Fields")
    private List<Object> fields;
    @JsonProperty("Boxsets")
    List<Object> boxSets;
}
