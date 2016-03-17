package com.gmail.ostapenkoyevgeniy.skynet.dao;

public class DaoException extends Exception {
    public DaoException() {
        super();
    }

    public DaoException(String detailMessage) {
        super(detailMessage);
    }

    public DaoException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public DaoException(Throwable throwable) {
        super(throwable);
    }
}
