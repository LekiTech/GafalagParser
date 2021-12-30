package org.lekitech.gafalag.sourcemodel;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * Date: 06.11.2021
 * Project: GafalagParser
 * Class: Fill - an array of rectangular area with solid color fills, same as lines,
 * each 'fill' object has 'x', 'y' in relative coordinates for positioning,
 * 'w' and 'h' for width and height in page unit,
 * plus 'clr' to reference a color with index in color dictionary. {@link Color}
 *
 * @author Enver Eskendarov (envereskendarov@gmail.com)
 * @version 1.0
 */
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Fill {

    private double x, y;
    @JsonProperty("w")
    private double width;
    @JsonProperty("h")
    private double height;
    @JsonProperty("clr")
    private int color;
    @JsonProperty("oc")
    private String originalColor;
}
