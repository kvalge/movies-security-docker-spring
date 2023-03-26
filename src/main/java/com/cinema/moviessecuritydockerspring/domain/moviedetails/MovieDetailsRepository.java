package com.cinema.moviessecuritydockerspring.domain.moviedetails;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MovieDetailsRepository extends JpaRepository<MovieDetails, Long> {
    MovieDetails findByMovieName(String name);

    @Query("select m from MovieDetails m where m.movie.id = ?1")
    MovieDetails findByMovieId(Long id);
}