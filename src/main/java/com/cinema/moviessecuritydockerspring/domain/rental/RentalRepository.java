package com.cinema.moviessecuritydockerspring.domain.rental;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RentalRepository extends JpaRepository<Rental, Long> {

    @Query("select r from Rental r where r.user.username = ?1")
    List<Rental> findByUsername(String username);
}