package com.gmail.shilovich.stas.jd2.datamodule.connection.sql.impl;

import com.gmail.shilovich.stas.jd2.datamodule.connection.sql.SQLReader;
import com.gmail.shilovich.stas.jd2.datamodule.exception.DatabaseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@Service
public class SQLReaderImpl implements SQLReader {

    private static final Logger logger = LogManager.getLogger(SQLReaderImpl.class);
    private static final String ERROR_MESSAGE = "SQL file reading error";

    @Override
    public String parseSQL(String path) {
        Resource resource = new ClassPathResource(path);
        try {
            path = resource.getURL().getPath();
        } catch (IOException e) {
            logger.error(ERROR_MESSAGE, e);
            throw new DatabaseException(ERROR_MESSAGE, e);
        }
        String result = "";
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            StringBuilder builder = new StringBuilder();
            String line = reader.readLine();
            while (line != null) {
                builder.append(line);
                builder.append(System.lineSeparator());
                line = reader.readLine();
            }
            result = builder.toString();
        } catch (IOException e) {
            logger.error(ERROR_MESSAGE, e);
            throw new DatabaseException(ERROR_MESSAGE, e);
        }
        return result;
    }
}
