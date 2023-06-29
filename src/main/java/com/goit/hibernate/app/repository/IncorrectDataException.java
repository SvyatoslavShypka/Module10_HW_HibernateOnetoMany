package com.goit.hibernate.app.repository;

public class IncorrectDataException extends Throwable {
    public IncorrectDataException() {
    }

    public IncorrectDataException(String message) {
        super(message);
    }

}
