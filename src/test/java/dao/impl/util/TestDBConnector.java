package dao.impl.util;

import com.zaxxer.hikari.HikariDataSource;
import dao.DBConnector;
import exception.DaoConnectionException;

import java.sql.Connection;
import java.sql.SQLException;

public class TestDBConnector extends DBConnector {
    private final HikariDataSource datasource;

    private static final String URL = "jdbc:h2:mem:beauty_test_db";
    private static final String USER = "admin";
    private static final String PASSWORD = "admin";

    public TestDBConnector() {
        datasource = new HikariDataSource();
        datasource.setJdbcUrl(URL);
        datasource.setUsername(USER);
        datasource.setPassword(PASSWORD);
    }

    public Connection getConnection() {
        try {
            return datasource.getConnection();
        } catch (SQLException e) {
            throw new DaoConnectionException("Can not obtain connection to the test H2 database", e);
        }
    }

}
