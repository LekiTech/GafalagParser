package org.lekitech.gafalag.dictionary.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

import java.util.List;

import static org.lekitech.gafalag.util.Resource.*;

@Data
@RequiredArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "name",
        "url",
        "sourceLangIso3",
        "targetLangIso3",
        "dictionary"
})
public class Dictionary {

    private final String name = DICTIONARY_NAME;
    private final String url = DICTIONARY_URL;
    private final String sourceLangIso3 = SOURCE_LANG_ISO_3;
    private final String targetLangIso3 = TARGET_LANG_ISO_3;
    private final @NonNull List<Article> dictionary;
}
