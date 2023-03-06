package com.cinema.moviessecuritydockerspring.infrastructure.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class DataNotFoundException extends RuntimeException {

    private String message;

    public DataNotFoundException(String message) {
        super(message);
        this.message = message;
    }
}
