package org.lekitech.gafalag;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.lekitech.gafalag.dictionary.mapper.Mapper;
import org.lekitech.gafalag.dictionary.mapper.MapperHandler;
import org.lekitech.gafalag.dictionary.model.Dictionary;

import java.io.File;
import java.io.IOException;

import static org.lekitech.gafalag.util.Resource.SOURCE_JSON_PATH;
import static org.lekitech.gafalag.util.Resource.TARGET_JSON_PATH;

/**
 * Date: 12.11.2021
 * Project: GafalagParser
 * Class: App
 *
 * @author Enver Eskendarov (envereskendarov@gmail.com)
 * @version 1.0
 */
public class App {

    public static void main(String[] args) throws IOException {
        final MapperHandler handler = new MapperHandler(SOURCE_JSON_PATH);
        final Mapper mapper = new Mapper(handler).init();
        final Dictionary dictionary = mapper.getDictionary();
        new ObjectMapper()
                .writerWithDefaultPrettyPrinter()
                .writeValues(new File(TARGET_JSON_PATH))
                .write(dictionary);
    }
}
