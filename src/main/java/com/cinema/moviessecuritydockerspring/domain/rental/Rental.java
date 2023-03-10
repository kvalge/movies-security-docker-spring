package com.cinema.moviessecuritydockerspring.domain.rental;

import com.cinema.moviessecuritydockerspring.domain.movie.Movie;
import com.cinema.moviessecuritydockerspring.domain.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "rental")
public class Rental {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "movie_id", nullable = false)
    private Movie movie;

    @NotNull
    @Column(name = "rental_date", nullable = false)
    private LocalDate rentalDate;

    @Column(name = "due_date")
    private LocalDate dueDate;

    @Column(name = "rating")
    private Integer rating;
}