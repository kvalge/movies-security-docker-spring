package com.cinema.moviessecuritydockerspring.domain.price;

import lombok.Data;

import java.io.Serializable;

@Data
public class PriceResponse implements Serializable {

    private String price;
}
