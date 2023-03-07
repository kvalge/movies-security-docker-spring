package com.cinema.moviessecuritydockerspring.domain.rental;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalRepository extends JpaRepository<Rental, Long> {
}