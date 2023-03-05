package com.cinema.moviessecuritydockerspring.domain.moviedetails;

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
    private String movieName;
}
