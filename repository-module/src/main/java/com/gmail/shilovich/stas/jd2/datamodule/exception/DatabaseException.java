package com.gmail.shilovich.stas.jd2.datamodule.exception;

public class DatabaseException extends RuntimeException {

    public DatabaseException(String errorMessage) {
        super(errorMessage);
    }

    public DatabaseException(String errorMessage, Throwable e) {
        super(errorMessage);
    }
}
