package com.cinema.moviessecuritydockerspring.analytics;

import lombok.Data;

import java.io.Serializable;

@Data
public class AnalyticsMovieResponse implements Serializable {

    private String movieName;
    private String categoryName;
    private String releaseYear;
    private Integer movieRentals;
    private Float shareOfRentals;
    private Float avMovieRating;
}
