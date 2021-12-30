package org.lekitech.gafalag.sourcemodel;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

/**
 * Date: 15.10.2021
 * Project: GafalagParser
 * Class: Run - an array of text run.
 * <p>
 * 'T': actual text
 * <p>
 * 'S': style index from style dictionary.
 * <p>
 * 'TS': [fontFaceId, fontSize, 1/0 for bold, 1/0 for italic]
 *
 * @author Enver Eskendarov (envereskendarov@gmail.com)
 * @version 1.0
 */
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Run {

    @JsonProperty("T")
    private String textBlock;
    @JsonProperty("S")
    private int style;
    @JsonProperty("TS")
    private List<Double> textStyle;
}
