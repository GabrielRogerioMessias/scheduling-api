package com.messias.schedulingapi.services.exceptionsServices;

public class CannotScheduleException extends RuntimeException {
    public CannotScheduleException() {
        super("It is not possible to schedule on this date. Please check the scheduling information.");
    }

    public CannotScheduleException(String message) {
        super(message);
    }
}
