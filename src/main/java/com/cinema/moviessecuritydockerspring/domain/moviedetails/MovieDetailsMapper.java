package com.cinema.moviessecuritydockerspring.domain.moviedetails;

import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface MovieDetailsMapper {

    @Mapping(target = "movie.name", source = "movieName")
    MovieDetails toEntity(MovieDetailsRequest movieDetailsRequest);

    @Mapping(target = "movieName", source = "movie.name")
    MovieDetailsRequest toDto(MovieDetails movieDetails);

    @Mapping(target = "movieName", source = "movie.name")
    MovieDetailsResponse toResponse(MovieDetails movieDetails);

    List<MovieDetailsResponse> toResponse(List<MovieDetails> movieDetails);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    MovieDetails partialUpdate(MovieDetailsRequest movieDetailsRequest, @MappingTarget MovieDetails movieDetails);
}