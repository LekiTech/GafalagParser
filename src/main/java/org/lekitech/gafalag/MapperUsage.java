package org.lekitech.gafalag;

/**
 * Date: 26.10.2021
 * Project: GafalagParser
 * Class: MapperUsage
 *
 * @author Enver Eskendarov (envereskendarov@gmail.com)
 * @version 1.0
 */
public class MapperUsage {

    public static void main(String[] args) {
        final JsonMapper jsonMapper = new JsonMapper("src/main/resources/dictionary.json");
        jsonMapper.printJSON();
        jsonMapper.writeJSON("result.json");
    }
}
