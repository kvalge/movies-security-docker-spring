package com.cinema.moviessecuritydockerspring.domain.rental;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class RentalResponse implements Serializable {

    private String username;
    private String movieName;
    private LocalDate rentalDate;
    private LocalDate dueDate;
    private Integer rating;
}
