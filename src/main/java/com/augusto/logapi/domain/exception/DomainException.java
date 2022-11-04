package com.augusto.logapi.domain.exception;

public class DomainException extends RuntimeException {

    private static final long serialVersionUID = 1;

    public DomainException(String message) {
        super(message);
    }
}
