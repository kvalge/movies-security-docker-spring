package com.cinema.moviessecuritydockerspring.infrastructure.exeption;

import lombok.Data;

@Data
public class DataNotFoundException extends RuntimeException {

    private String message;

    public DataNotFoundException(String message) {
        super(message);
        this.message = message;
    }
}
