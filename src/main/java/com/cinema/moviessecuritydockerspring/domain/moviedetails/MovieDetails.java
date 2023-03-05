package com.cinema.moviessecuritydockerspring.domain.moviedetails;

import com.cinema.moviessecuritydockerspring.domain.movie.Movie;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "movie_details")
public class MovieDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 250)
    @Column(name = "director", length = 250)
    private String director;

    @Size(max = 500)
    @Column(name = "writer", length = 500)
    private String writer;

    @Size(max = 500)
    @Column(name = "stars", length = 500)
    private String stars;

    @Size(max = 50)
    @Column(name = "year", length = 50)
    private String year;

    @Size(max = 500)
    @Column(name = "country", length = 500)
    private String country;

    @Size(max = 250)
    @NotNull
    @Column(name = "description", nullable = false, length = 250)
    private String description;

    @NotNull
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "movie_id", nullable = false)
    private Movie movie;
}