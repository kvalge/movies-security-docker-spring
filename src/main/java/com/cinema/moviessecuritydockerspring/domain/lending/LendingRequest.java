package com.cinema.moviessecuritydockerspring.domain.lending;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class LendingRequest implements Serializable {

    private String username;
    private String movieName;
    private LocalDate lendingDate;
}
