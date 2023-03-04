package com.cinema.moviessecuritydockerspring.infrastructure.exception;

import lombok.Data;

@Data
public class DataExistsException extends RuntimeException {

    private String message;

    public DataExistsException(String message) {
        super(message);
        this.message = message;
    }
}
