package com.cinema.moviessecuritydockerspring.domain.movie;

import lombok.Data;

import java.io.Serializable;

@Data
public class MovieResponse implements Serializable {

    private String movieName;
    private String categoryName;
    private Float avRating;
}
