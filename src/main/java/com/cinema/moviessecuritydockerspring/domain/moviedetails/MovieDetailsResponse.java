package com.cinema.moviessecuritydockerspring.domain.moviedetails;

import lombok.Data;

@Data
public class MovieDetailsResponse {

    private String director;
    private String writer;
    private String stars;
    private String year;
    private String country;
    private String description;
    private String price;
    private String image;
    private String movieName;
}
