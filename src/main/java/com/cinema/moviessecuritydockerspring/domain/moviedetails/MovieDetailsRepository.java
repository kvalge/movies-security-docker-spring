package com.cinema.moviessecuritydockerspring.domain.moviedetails;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieDetailsRepository extends JpaRepository<MovieDetails, Long> {
    MovieDetails findByMovieName(String name);
}