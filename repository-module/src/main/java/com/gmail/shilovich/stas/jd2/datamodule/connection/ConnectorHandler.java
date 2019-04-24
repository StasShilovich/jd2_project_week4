package com.gmail.shilovich.stas.jd2.datamodule.connection;

import com.gmail.shilovich.stas.jd2.datamodule.exception.DatabaseException;
import com.gmail.shilovich.stas.jd2.datamodule.properties.DatabaseProperties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;


@Repository
public class ConnectorHandler {

    private static final Logger logger = LogManager.getLogger(ConnectorHandler.class);
    private static final String ERROR_MESSAGE = "Connection failed";
    private final DatabaseProperties databaseProperties;

    @Autowired
    public ConnectorHandler(DatabaseProperties databaseProperties) {
        try {
            Class.forName(databaseProperties.getDriver());
        } catch (ClassNotFoundException e) {
            logger.error(ERROR_MESSAGE, e);
            throw new DatabaseException(ERROR_MESSAGE, e);
        }
        this.databaseProperties = databaseProperties;
    }

    public Connection getConnection() {
        try {
            Properties properties = new Properties();
            properties.setProperty("user", databaseProperties.getUsername());
            properties.setProperty("password", databaseProperties.getPassword());
            properties.setProperty("useUnicode", "true");
            properties.setProperty("characterEncoding", "cp1251");
            return DriverManager.getConnection(databaseProperties.getUrl(), properties);
        } catch (SQLException e) {
            logger.error(ERROR_MESSAGE, e);
            throw new DatabaseException(ERROR_MESSAGE, e);
        }
    }

    @PostConstruct
    public void createDatabaseTables() {
        String createTableQuery = "CREATE TABLE IF NOT EXISTS t_item(f_id INT NOT NULL AUTO_INCREMENT," +
                " f_name  VARCHAR(40) NOT NULL, f_status VARCHAR(45) NOT NULL," +
                " f_deleted TINYINT NOT NULL, PRIMARY KEY (f_id))";
        executeQuery(createTableQuery);
    }

    @PostConstruct
    public void insertDatabaseStartParameters() {
        String createTableQuery = "INSERT INTO t_item(f_id, f_name, f_status, f_deleted)" +
                " VALUES (1, 'Test Item','ready',0)";
        executeQuery(createTableQuery);
    }

    private void executeQuery(String createTableQuery) {
        try (Connection connection = getConnection()) {
            connection.setAutoCommit(false);
            try (Statement statement = connection.createStatement()) {
                statement.execute(createTableQuery);
                connection.commit();
            } catch (Exception e) {
                logger.error(ERROR_MESSAGE, e);
                connection.rollback();
            }
        } catch (SQLException e) {
            logger.error(ERROR_MESSAGE, e);
            throw new DatabaseException(ERROR_MESSAGE);
        }
    }
}
