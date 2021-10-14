package org.lekitech.gafalag;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.lekitech.gafalag.dictionarymodels.DefWord;
import org.lekitech.gafalag.dictionarymodels.Dictionary;
import org.lekitech.gafalag.dictionarymodels.Word;
import org.lekitech.gafalag.jsonmodels.Root;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

/**
 * Date: 07.10.2021
 * Project: GafalagParser
 * Class: JsonParser
 *
 * @author Enver Eskendarov (envereskendarov@gmail.com)
 * @version 1.0
 */
public class JsonParser {

    public static void main(String[] args) throws IOException {
        final Dictionary words = new Dictionary();
        final ObjectMapper mapper = new ObjectMapper();
        final File file = new File("D:\\Java\\GafalagParser\\src\\main\\resources\\page.json");
        final Root root = mapper.readValue(file, Root.class);

        root.texts.forEach(text -> {
            final String color = text.dictTextColor;
            if (text.y > 1.5f) {
                final Word word = new Word(
                        text.r.get(0).value,
                        Objects.equals(color, "#003365"),
                        Objects.equals(color, "#261300"),
                        Objects.equals(color, null)
                );
                words.add(word);
            }
        });
        words.getDictionary().forEach((key, value) -> {
            try {
                mapper.enable(SerializationFeature.INDENT_OUTPUT);
                final DefWord word = new DefWord(key, value);
                final String json = mapper.writeValueAsString(word);
                System.out.println(json);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        });
    }
}
