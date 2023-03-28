package com.cinema.moviessecuritydockerspring.domain.rental;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RentalRepository extends JpaRepository<Rental, Long> {

    @Query("select r from Rental r where r.user.username = ?1 and upper(r.movie.name) = upper(?2)")
    Rental findByUsernameAndMovieName(String username, String name);

    @Query("select r from Rental r where r.user.username = ?1")
    List<Rental> findByUsername(String username);

    @Query("select r from Rental r where upper(r.movie.name) = upper(?1)")
    List<Rental> findByMovieName(String name);

    @Query("select r from Rental r where r.movie.id = ?1")
    List<Rental> findByMovieId(Long id);
}