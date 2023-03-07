package com.cinema.moviessecuritydockerspring.domain.rental;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class RentalRequest implements Serializable {

    private String username;
    private String movieName;
    private LocalDate rentalDate;
}
