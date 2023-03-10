package com.cinema.moviessecuritydockerspring.analytics;

import lombok.Data;

import java.io.Serializable;

@Data
public class AnalyticsCategoryResponse implements Serializable {

    private String categoryName;
    private Integer moviesInCategory;
    private Float shareOfMovies;
    private Integer rentalsInCategory;
    private Float shareOfRentals;
    private Float avRatingInCategory;
}
