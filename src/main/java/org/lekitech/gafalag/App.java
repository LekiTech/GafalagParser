package org.lekitech.gafalag;

import org.lekitech.gafalag.dictionarymodels.Article;
import org.lekitech.gafalag.jsonmodels.Deserializer;

import java.util.List;

/**
 * Date: 12.11.2021
 * Project: GafalagParser
 * Class: App
 *
 * @author Enver Eskendarov (envereskendarov@gmail.com)
 * @version 1.0
 */
public class App {

    public static void main(String[] args) {
        final DictionaryMapper mapper = new DictionaryMapper(new Deserializer("dictionary.json"));
        final List<Article> dictionary = mapper.init().getDictionary();
        final DBConnection connection = new DBConnection();
        dictionary.forEach(connection::add);
    }
}
