package com.gmail.shilovich.stas.jd2.datamodule.connection;

import com.gmail.shilovich.stas.jd2.datamodule.connection.sql.SQLReader;
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
    private final SQLReader sqlReader;

    @Autowired
    public ConnectorHandler(DatabaseProperties databaseProperties, SQLReader sqlReader) {
        try {
            Class.forName(databaseProperties.getDriver());
        } catch (ClassNotFoundException e) {
            logger.error(ERROR_MESSAGE, e);
            throw new DatabaseException(ERROR_MESSAGE, e);
        }
        this.databaseProperties = databaseProperties;
        this.sqlReader = sqlReader;
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
        executeFile("/static/tables.sql");
    }

//    @PostConstruct
//    public void insertForeignKey() {
//        executeFile("/static/foreignKey.sql");
//    }

//    @PostConstruct
//    public void insertDatabaseStartParameters() {
//        executeFile("/static/startParameters.sql");
//    }

    private void executeFile(String path) {
        String result = sqlReader.parseSQL(path);
        String[] array = result.split(";");
        try (Connection connection = getConnection()) {
            connection.setAutoCommit(false);
            try (Statement statement = connection.createStatement()) {
                /*
                 Arrays.stream(array)
                        .map(String::trim)
                        .map(statement::addBatch);
                 */
                for (String s : array) {
                    statement.addBatch(s.trim());
                }
                statement.executeBatch();
                connection.commit();
            } catch (SQLException e) {
                logger.error(ERROR_MESSAGE, e);
                connection.rollback();
            }
        } catch (SQLException e) {
            logger.error(ERROR_MESSAGE, e);
            throw new DatabaseException(ERROR_MESSAGE);
        }
    }
}