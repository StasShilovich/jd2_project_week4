package com.gmail.shilovich.stas.jd2.servicemodule.exception;

public class ServiceException extends RuntimeException {

    public ServiceException(String errorMessage) {
        super(errorMessage);
    }

    public ServiceException(String errorMessage, Throwable e) {
        super(errorMessage);
    }
}
