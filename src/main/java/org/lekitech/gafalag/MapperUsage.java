package org.lekitech.gafalag;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.File;
import java.io.IOException;

/**
 * Date: 26.10.2021
 * Project: GafalagParser
 * Class: MapperUsage
 *
 * @author Enver Eskendarov (envereskendarov@gmail.com)
 * @version 1.0
 */
public class MapperUsage {

    private final Mapper mapper;

    public MapperUsage(String srcFile) {
        this.mapper = new Mapper(srcFile).init();
    }

    public void toJSONFile(String target) {
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValues(new File(target)).write(mapper.getDictionary());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printJSON() {
        try {
            System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(mapper.getDictionary()));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        final MapperUsage mapperUsage = new MapperUsage("src/main/resources/dictionary.json");
        mapperUsage.toJSONFile("lezgi-rus-dict.json");
    }
}
