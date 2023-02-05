package com.cinema.moviessecuritydockerspring.domain.category;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serializable;

@Data
public class CategoryDto implements Serializable {

    private final Long id;
    @Size(max = 250)
    @NotNull
    private final String name;
}
