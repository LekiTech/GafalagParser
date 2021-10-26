package org.lekitech.gafalag.dictionarymodels;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Date: 15.10.2021
 * Project: GafalagParser
 * <p>
 * Class: EntryWord - Словарная статья, состоящая из заглавной единицы и текста,
 * разъясняющего заголовочную единицу и описывающего её основные характеристики.
 *
 * @author Enver Eskendarov (envereskendarov@gmail.com)
 * @version 1.0
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class EntryWord {

    /**
     * Заглавное слово, сведения о его орфографии, произношении, ударении.
     */
    @JsonProperty("head")
    private String head;
    /**
     * Грамматическая зона: указание грамматических категорий (часть речи, род, вид и т.п.), опорные словоформы;
     * возможные варианты.
     */
    @JsonProperty("inflection")
    private String inflection;
    /**
     * Зона значения: семантика, фразеологизмы, метки
     */
    @JsonProperty("definition")
    private List<String> definitions;
}
