package com.cinema.moviessecuritydockerspring.analytics;

import lombok.Data;

import java.io.Serializable;

@Data
public class AnalyticsYearResponse implements Serializable {

    private String releaseYear;
    private Integer movieRentals;
    private Float shareOfRentals;
    private Float avMovieRating;
}
