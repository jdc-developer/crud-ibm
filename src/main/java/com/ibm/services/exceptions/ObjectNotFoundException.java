package com.ibm.services.exceptions;

public class ObjectNotFoundException extends RuntimeException {

    private static final String MESSAGE = "The requested object was not found. Please, try another key";

    public ObjectNotFoundException() {
        super(MESSAGE);
    }

    public ObjectNotFoundException(String msg ) {
        super(msg);
    }
}
