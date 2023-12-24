package com.messias.schedulingapi.services.exceptionsServices;

public class DatabaseException extends RuntimeException {
    public DatabaseException(String msg) {
        super(msg);
    }
}
