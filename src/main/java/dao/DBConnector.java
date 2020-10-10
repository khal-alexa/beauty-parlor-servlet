package dao;

import com.zaxxer.hikari.HikariDataSource;
import exception.DaoConnectionException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DBConnector {
    private static final Logger LOGGER = LogManager.getLogger(DBConnector.class);

    private static final String DB_CONFIG_FILENAME = "db";
    private static final HikariDataSource hikariPool = new HikariDataSource();

    public DBConnector() {
        initDataSource();
    }

    public Connection getConnection() {
        try {
            return hikariPool.getConnection();
        } catch (SQLException e) {
            String message = "Can not establish connection to database";
            LOGGER.warn(message, e);
            throw new DaoConnectionException(message, e);
        }
    }

    private void initDataSource() {
        ResourceBundle rb = ResourceBundle.getBundle(DB_CONFIG_FILENAME);
        hikariPool.setJdbcUrl(rb.getString("db.url"));
        hikariPool.setUsername(rb.getString("db.user"));
        hikariPool.setPassword(rb.getString("db.password"));
        hikariPool.setDriverClassName(rb.getString("db.driver"));
        hikariPool.setMinimumIdle(Integer.parseInt(rb.getString("db.minIdle")));
        hikariPool.setMaximumPoolSize(Integer.parseInt(rb.getString("db.maxIdle")));
    }

}
