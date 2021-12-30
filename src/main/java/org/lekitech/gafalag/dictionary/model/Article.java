package org.lekitech.gafalag.dictionary.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

import java.util.List;

/**
 * Date: 15.10.2021
 * Project: GafalagParser
 * <p>
 * Class: Article - Словарная статья, состоящая из заглавной единицы и текста,
 * разъясняющего заголовочную единицу и описывающего её основные характеристики.
 *
 * @author Enver Eskendarov (envereskendarov@gmail.com)
 * @version 1.0
 */
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "spelling",
        "inflection",
        "definitions"
})
public class Article {

    /**
     * Заглавное слово, сведения о его орфографии, произношении, ударении.
     */
    private final @NonNull String spelling;
    /**
     * Опорные словоформы, возможные варианты.
     */
    private String inflection;
    /**
     * Зона значения: семантика, фразеологизмы, метки
     */
    private final @NonNull List<String> definitions;
}
