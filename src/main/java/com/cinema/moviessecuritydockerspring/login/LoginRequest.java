package com.cinema.moviessecuritydockerspring.login;

import lombok.Data;

import java.io.Serializable;

@Data
public class LoginRequest implements Serializable {

    private String usernameOrEmail;
    private String password;
}
