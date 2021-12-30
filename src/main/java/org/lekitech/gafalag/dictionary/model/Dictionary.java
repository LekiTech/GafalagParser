package org.lekitech.gafalag.dictionary.model;

import com.fasterxml.jackson.annotation.*;
import lombok.*;
import org.lekitech.gafalag.util.Resource;

import java.util.List;

import static org.lekitech.gafalag.util.Resource.*;

@Data
@RequiredArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "name",
        "url",
        "expressionLanguageIso3",
        "definitionLanguageIso3",
        "dictionary"
})
@JsonRootName("source")
public class Dictionary {

    private final String name = DICTIONARY_NAME;
    private final String url = DICTIONARY_URL;
    private final String expressionLanguageIso3 = EXPRESSION_LANGUAGE_ISO_3;
    private final String definitionLanguageIso3 = DEFINITION_LANGUAGE_ISO_3;
    private final @NonNull List<Article> dictionary;
}
