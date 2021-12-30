package org.lekitech.gafalag.dictionary.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Value;

import java.util.List;

import static org.lekitech.gafalag.util.Resource.*;

@Value
@JsonPropertyOrder({
        "name",
        "url",
        "sourceLangIso3",
        "targetLangIso3",
        "dictionary"
})
public class Dictionary {

    String name = DICTIONARY_NAME;
    String url = DICTIONARY_URL;
    String sourceLangIso3 = SOURCE_LANG_ISO_3;
    String targetLangIso3 = TARGET_LANG_ISO_3;
    List<Article> dictionary;
}
