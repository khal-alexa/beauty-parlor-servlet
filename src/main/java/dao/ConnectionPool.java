package dao;

import dao.exception.SqlQueryExecutionException;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ConnectionPool {
    private final String url;
    private final String user;
    private final String password;
    private List<Connection> connectionPool;
    private List<Connection> usedConnections = new ArrayList<>();
    private static final int INITIAL_POOL_SIZE = 15;
    private static final String QUERY_DELIMITER = "/\\*[\\s\\S]*?\\*/|--[^\\r\\n]*|;";

    private ConnectionPool(String url, String user, String password, List<Connection> connectionPool) {
        this.url = url;
        this.user = user;
        this.password = password;
        this.connectionPool = connectionPool;
        initTables();
    }

    public static ConnectionPool create() {
        List<Connection> pool = new ArrayList<>(INITIAL_POOL_SIZE);
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("src/main/resources/db.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String url = properties.getProperty("db.url");
        String user = properties.getProperty("db.user");
        String password = properties.getProperty("db.password");
        for (int i = 0; i < INITIAL_POOL_SIZE; i++) {
            pool.add(createConnection(url, user, password));
        }
        return new ConnectionPool(url, user, password, pool);
    }

    public Connection getConnection() {
        Connection connection = connectionPool
                .remove(connectionPool.size() - 1);
        usedConnections.add(connection);
        return connection;
    }

    public boolean releaseConnection(Connection connection) {
        connectionPool.add(connection);
        return usedConnections.remove(connection);
    }

    private static Connection createConnection(String url, String user, String password) {
        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void initTables() {
        String createTablesSql = getStringWithSQLQueryFromFile("src/main/resources/schema.sql");
        executeFewQueries(createTablesSql);
    }

    public static String getStringWithSQLQueryFromFile(String filePath) {
        Path path = Paths.get(filePath);
        return convertSQLFileToString(path);
    }

    private static String convertSQLFileToString(Path path) {
        String query = "";
        try {
            query = String.join("\n", Files.readAllLines(path));
        } catch (IOException e) {
            e.getCause();
        }
        return query;
    }

    private void executeFewQueries(String query) {
        String[] queries = query.split(QUERY_DELIMITER);
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            for (String queryLine : queries) {
                queryLine = queryLine.replace("\n", "");
                if (!queryLine.isEmpty()) {
                    statement.execute(queryLine.trim());
                }
            }
        } catch (SQLException e) {
            String message = "Fail to init DB tables";
            throw new SqlQueryExecutionException(message, e);
        }
    }

}
