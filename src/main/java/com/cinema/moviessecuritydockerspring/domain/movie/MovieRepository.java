package com.cinema.moviessecuritydockerspring.domain.movie;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    Movie findByName(String name);
}
