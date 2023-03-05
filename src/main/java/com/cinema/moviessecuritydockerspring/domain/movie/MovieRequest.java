package com.cinema.moviessecuritydockerspring.domain.movie;

import lombok.Data;

import java.io.Serializable;

@Data
public class MovieRequest implements Serializable {

    private String name;
    private String categoryName;
}
