package dao.impl.util;

import dao.DBConnector;
import dao.impl.exception.DaoTestException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class TestDBInitializer {
    private final DBConnector dbConnector;

    private static final String SCHEMA_SQL_PATH = "src/main/resources/schema.sql";
    private static final String POPULATION_SQL_PATH = "src/main/resources/population.sql";

    public TestDBInitializer(DBConnector dbConnector) {
        this.dbConnector = dbConnector;
    }

    public void initDatabase() {
        String query = parseSqlFileToString(SCHEMA_SQL_PATH);
        executeQuery(query);
    }

    public void populateDatabase() {
        String query = parseSqlFileToString(POPULATION_SQL_PATH);
        executeQuery(query);
    }

    private void executeQuery(String query) {
        try (Connection connection = dbConnector.getConnection()) {
            Statement createTableStatement = connection.createStatement();
            createTableStatement.execute(query);
        } catch (SQLException e) {
            throw new DaoTestException("Failed execution of query: " + query, e);
        }
    }

    private static String parseSqlFileToString(String filePath) {
        Path path = Paths.get(filePath);
        return parseSqlFileToString(path);
    }

    private static String parseSqlFileToString(Path path) {
        try {
            return String.join("\n", Files.readAllLines(path));
        } catch (IOException e) {
            throw new DaoTestException
                    (String.format("Parsing sql to string failed for file with path=%s", path.toAbsolutePath()), e);
        }
    }

}
