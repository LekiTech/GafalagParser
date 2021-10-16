package org.lekitech.gafalag;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.lekitech.gafalag.structuredmodels.Page;
import org.lekitech.gafalag.structuredmodels.Text;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.function.Consumer;

/**
 * Date: 15.10.2021
 * Project: GafalagParser
 * Class: JsonMapper
 *
 * @author Enver Eskendarov (envereskendarov@gmail.com)
 * @version 1.0
 */
public class JsonMapper {

    public static void main(String[] args) throws IOException {
        final ObjectMapper mapper = new ObjectMapper();
        final File file = new File("src/main/resources/dictionary.json");
        final List<Page> pages = mapper.readerFor(new TypeReference<List<Page>>() {
        }).readValue(file);
        final Consumer<Text> print = text -> {
            if (text.isNewWord()) {
                System.out.println();
            }
            System.out.print(text.get());
        };
        pages.stream().limit(4).flatMap(page -> page.getTextData().stream()).forEach(print);
    }
}
