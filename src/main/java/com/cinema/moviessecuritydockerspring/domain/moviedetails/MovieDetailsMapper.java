package com.cinema.moviessecuritydockerspring.domain.moviedetails;

import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface MovieDetailsMapper {

    @Mapping(target = "movie.name", source = "movieName")
    MovieDetails toEntity(MovieDetailsRequest movieDetailsRequest);

    @Mapping(target = "movieName", source = "movie.name")
    MovieDetailsRequest toDto(MovieDetails movieDetails);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    MovieDetails partialUpdate(MovieDetailsRequest movieDetailsRequest, @MappingTarget MovieDetails movieDetails);
}