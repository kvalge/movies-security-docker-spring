package com.cinema.moviessecuritydockerspring.domain.register;

import lombok.Data;

import java.io.Serializable;

@Data
public class RegisterRequest implements Serializable {

    private String name;
    private String username;
    private String email;
    private String password;
}
