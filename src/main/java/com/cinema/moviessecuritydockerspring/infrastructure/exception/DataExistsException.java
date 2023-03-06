package com.cinema.moviessecuritydockerspring.infrastructure.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class DataExistsException extends RuntimeException {

    private String message;

    public DataExistsException(String message) {
        super(message);
        this.message = message;
    }
}
