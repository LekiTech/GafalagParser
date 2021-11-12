package org.lekitech.gafalag;

import org.lekitech.gafalag.dictionarymodels.Article;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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

    private static final String QUERY_INSERT = "INSERT INTO lez_rus(expression, inflection, definition) VALUES (?,?,?)";
    private static final String QUERY_SELECT_BY_ID = "SELECT * FROM lez_rus WHERE id = ?";
    private static final String QUERY_SELECT_BY_EXP = "SELECT * FROM lez_rus WHERE expression = ?";
    private static final String QUERY_SELECT_ALL = "SELECT * FROM lez_rus";
    private static final String QUERY_UPDATE = "UPDATE lez_rus SET expression = ?, inflection = ?, definition = ? WHERE id = ?";
    private static final String QUERY_DELETE = "DELETE FROM lez_rus WHERE id = ?";

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

    public boolean add(Article article) {
        boolean res = false;
        try (PreparedStatement statement = connection.prepareStatement(QUERY_INSERT)) {
            statement.setString(1, article.getExpression());
            statement.setString(2, article.getInflection());
            statement.setArray(3, connection.createArrayOf(
                    "VARCHAR", article.getDefinition().toArray()
            ));
            res = statement.execute();
        } catch (SQLException e) {
            printSQLException(e);
        }
        return res;
    }

    public void addAll(List<Article> dictionary) {
        int count = 0;
        try (PreparedStatement statement = connection.prepareStatement(QUERY_INSERT)) {
            for (Article article : dictionary) {
                statement.setString(1, article.getExpression());
                statement.setString(2, article.getInflection());
                statement.setArray(3, connection.createArrayOf(
                        "VARCHAR", article.getDefinition().toArray()
                ));
                statement.addBatch();
                count++;
                // execute every 100 rows or less
                if (count % 100 == 0 || count == dictionary.size()) {
                    statement.executeBatch();
                }
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        if (count == dictionary.size()) {
            System.out.println("Successfully added to DB!");
        }
    }

    public Article getArticleById(int id) {
        Article article = new Article();
        try (PreparedStatement statement = connection.prepareStatement(QUERY_SELECT_BY_ID)) {
            statement.setInt(1, id);
            final ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                article = new Article(
                        rs.getString("expression"),
                        rs.getString("inflection"),
                        List.of((String[]) rs.getArray("definition").getArray())
                );
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return article;
    }

    public List<Article> getArticleByExp(String exp) {
        List<Article> articles = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(QUERY_SELECT_BY_EXP)) {
            statement.setString(1, exp);
            final ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                articles.add(new Article(
                        rs.getString("expression"),
                        rs.getString("inflection"),
                        List.of((String[]) rs.getArray("definition").getArray())
                ));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return articles;
    }

    public List<Article> getAllArticles() {
        List<Article> dictionary = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(QUERY_SELECT_ALL)) {
            final ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                dictionary.add(new Article(
                        rs.getString("expression"),
                        rs.getString("inflection"),
                        List.of((String[]) rs.getArray("definition").getArray())
                ));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return dictionary;
    }

    public boolean updateArticle(int id, Article article) {
        boolean res = false;
        try (PreparedStatement statement = connection.prepareStatement(QUERY_UPDATE)) {
            statement.setInt(4, id);
            statement.setString(1, article.getExpression());
            statement.setString(2, article.getInflection());
            statement.setArray(3,
                    connection.createArrayOf("VARCHAR", article.getDefinition().toArray())
            );
            res = statement.executeUpdate() != 1;
        } catch (SQLException e) {
            printSQLException(e);
        }
        return res;
    }

    public boolean deleteArticle(int id) {
        boolean res = false;
        try (PreparedStatement preparedStatement = connection.prepareStatement(QUERY_DELETE)) {
            preparedStatement.setInt(1, id);
            res = preparedStatement.executeUpdate() != 1;
        } catch (SQLException e) {
            printSQLException(e);
        }
        return res;
    }

    public static void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                final SQLException exception = (SQLException) e;
                e.printStackTrace(System.err);
                System.err.printf(
                        "SQLState: %s, Error Code: %s, %s\n",
                        exception.getSQLState(),
                        exception.getErrorCode(),
                        exception.getMessage()
                );
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Caused by: " + t);
                    t = t.getCause();
                }
            }
        }
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }
}
