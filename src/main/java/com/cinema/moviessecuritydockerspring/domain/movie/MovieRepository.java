package com.cinema.moviessecuritydockerspring.domain.movie;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    Movie findByName(String name);

    List<Movie> findByCategory(String name);
}
