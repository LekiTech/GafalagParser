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
        final DictionaryMapper dictionaryMapper = new DictionaryMapper("src/main/resources/dictionary.json");
        dictionaryMapper.printJSON();
//        dictionaryMapper.writeJSON("result.json");
    }
}
