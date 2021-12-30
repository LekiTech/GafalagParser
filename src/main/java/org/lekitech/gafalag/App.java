package org.lekitech.gafalag;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SequenceWriter;
import lombok.SneakyThrows;
import org.lekitech.gafalag.dictionarymodels.Article;
import org.lekitech.gafalag.jsonmodels.Deserializer;

import java.io.File;
import java.io.IOException;
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

    @SneakyThrows(value = IOException.class)
    public static void main(String[] args) {
        final DictionaryMapper mapper = new DictionaryMapper(new Deserializer("srcfiles/dictionary.json"));
        final List<Article> dictionary = mapper.init().getDictionary();
        SequenceWriter jsonToFile = new ObjectMapper().writerWithDefaultPrettyPrinter().writeValues(new File("lezgi_russian_dictionary.json"));
        jsonToFile.write(dictionary);

        // Transaction transaction = null;
        // try (Session session = HibernateUtil.getSessionFactory().openSession()) {
        //     final Language lezgi = new Language("Lezgi", "lz", "lez");
        //     final Language russian = new Language("Russian", "ru", "rus");
        //     transaction = session.beginTransaction();
        //     session.save(lezgi);
        //     session.save(russian);
        //     transaction.commit();
        // } catch (Exception e) {
        //     if (transaction != null) {
        //         transaction.rollback();
        //     }
        //     e.printStackTrace();
        // }
        // final DBConnection connection = new DBConnection();
        // dictionary.forEach(connection::add);
    }
}
