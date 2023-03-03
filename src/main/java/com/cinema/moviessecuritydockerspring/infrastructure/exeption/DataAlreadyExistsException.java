package com.cinema.moviessecuritydockerspring.infrastructure.exeption;

import lombok.Data;

@Data
public class DataAlreadyExistsException extends RuntimeException {

    private String message;

    public DataAlreadyExistsException(String message) {
        super(message);
        this.message = message;
    }
}
