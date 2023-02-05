package com.cinema.moviessecuritydockerspring.domain.movie;

import com.cinema.moviessecuritydockerspring.domain.category.Category;
import com.cinema.moviessecuritydockerspring.domain.category.CategoryDto;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serializable;

@Data
public class MovieDto implements Serializable {

    private final Long id;
    @Size(max = 250)
    @NotNull
    private final String name;
    private final String description;
    private final CategoryDto category;
}
