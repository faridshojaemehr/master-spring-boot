package com.master.spring_boot.exception;

public class DuplicateResourseException extends RuntimeException {
    public DuplicateResourseException(String message) {
        super(message);
    }

    public DuplicateResourseException(String message, Throwable cause) {
        super(message,cause);
    }
}
