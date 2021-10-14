package org.lekitech.gafalag.dictionarymodels;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Date: 14.10.2021
 * Project: GafalagParser
 * Class: DefWord
 *
 * @author Enver Eskendarov (envereskendarov@gmail.com)
 * @version 1.0
 */
@JsonInclude(Include.NON_NULL)
public class DefWord {

    @JsonProperty("word")
    private String word;
    @JsonProperty("participle")
    private String participle;
    @JsonProperty("definitions")
    private Map<String, List<String>> definitions;

    public DefWord(String word, String vals) {
        final String[] wordParts = word.split("[()]");
        this.word = wordParts[0].trim();
        if (wordParts.length != 1) {
            this.participle = wordParts[1].trim();
        }
        int countDef = 0;
        final Map<String, List<String>> defDic = new LinkedHashMap<>();
        for (String valDef : vals.split("\\d([).])")) {
            if (!valDef.trim().isBlank()) {
                final List<String> defPart = new ArrayList<>();
                for (String valEx : valDef.split(";")) {
                    if (!valEx.trim().isBlank()) {
                        defPart.add(valEx.trim());
                    }
                }
                defDic.put("def_" + (++countDef), defPart);
            }
        }
        this.definitions = defDic;
    }
}
