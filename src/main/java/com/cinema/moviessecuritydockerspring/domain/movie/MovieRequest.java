package com.cinema.moviessecuritydockerspring.domain.movie;

import com.cinema.moviessecuritydockerspring.domain.category.CategoryDto;
import lombok.Data;

import java.io.Serializable;

@Data
public class MovieRequest implements Serializable {

    private String name;
    private String description;
    private String categoryName;
}
