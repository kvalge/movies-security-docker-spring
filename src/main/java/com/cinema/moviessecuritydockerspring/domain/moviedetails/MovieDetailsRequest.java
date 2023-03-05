package com.cinema.moviessecuritydockerspring.domain.moviedetails;

import com.cinema.moviessecuritydockerspring.domain.movie.Movie;
import lombok.Data;

import java.io.Serializable;

@Data
public class MovieDetailsRequest implements Serializable {

    private Long id;
    private String director;
    private String writer;
    private String stars;
    private String year;
    private String country;
    private String description;
    private Movie movie;
}
