package com.cinema.moviessecuritydockerspring.domain.lending;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LendingRepository extends JpaRepository<Lending, Long> {
}