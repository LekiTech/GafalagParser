package org.lekitech.gafalag.structuredmodels;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

/**
 * Date: 15.10.2021
 * Project: GafalagParser
 * Class: R
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
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class R {

    @JsonProperty("T")
    private String text;
    /**
     * 00 - "QuickType,Arial,Helvetica,sans-serif",							> QuickType - sans-serif variable font
     * 01 - "QuickType Condensed,Arial Narrow,Arial,Helvetica,sans-serif",	> QuickType Condensed - thin sans-serif variable font
     * 02 - "QuickTypePi",													> QuickType Pi
     * 03 - "QuickType Mono,Courier New,Courier,monospace",					> QuickType Mono - san-serif fixed font
     * 04 - "OCR-A,Courier New,Courier,monospace",							> OCR-A - OCR readable san-serif fixed font
     * 05 - "OCR B MT,Courier New,Courier,monospace"						> OCR-B MT - OCR readable san-serif fixed font
     */
    @JsonProperty("S")
    private int style;
    /**
     * TS: [Face, Size, Bold(0,1), Italic(0,1)]
     */
    @JsonProperty("TS")
    private List<Double> fontType;
}
