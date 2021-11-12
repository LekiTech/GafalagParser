package org.lekitech.gafalag;

import org.json.JSONArray;
import org.lekitech.gafalag.dictionarymodels.Article;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Date: 12.11.2021
 * Project: GafalagParser
 * Class: DBConnection
 *
 * @author Enver Eskendarov (envereskendarov@gmail.com)
 * @version 1.0
 */
public class DBConnection implements AutoCloseable {

    private Connection connection;

    public DBConnection() {
        init();
    }

    public DBConnection(Connection connection) {
        this.connection = connection;
    }

    public void init() {
        try {
            final Properties properties = new Properties();
            properties.load(new FileInputStream("database/db.properties"));
            connection = DriverManager.getConnection(
                    properties.getProperty("psql.url"),
                    properties.getProperty("psql.username"),
                    properties.getProperty("psql.password")
            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    public void add(Article article) {
        try (PreparedStatement statement = connection
                .prepareStatement("insert into lez_rus(expression, inflection, definition) values (?,?,?)")
        ) {
            statement.setString(1, article.getExpression());
            statement.setString(2, article.getInflection());
            statement.setString(3, new JSONArray(article.getDefinition()).toString());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }
}
