package com.cinema.moviessecuritydockerspring.domain.user;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserResponse implements Serializable {

    private String name;
    private String email;
}
