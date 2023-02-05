package com.cinema.moviessecuritydockerspring.domain.movie;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface MovieMapper {

    @Mapping(target = "category.name", source = "categoryName")
    Movie toEntity(MovieRequest request);

    @Mapping(target = "categoryName", source = "category.name")
    MovieRequest toRequest(Movie movie);

    List<MovieRequest> toRequest(List<Movie> movies);
}
