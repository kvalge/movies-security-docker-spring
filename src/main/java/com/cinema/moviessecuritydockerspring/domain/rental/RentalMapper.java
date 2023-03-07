package com.cinema.moviessecuritydockerspring.domain.rental;

import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface RentalMapper {
    @Mapping(target = "user.username", source = "username")
    @Mapping(target = "movie.name", source = "movieName")
    Rental toEntity(RentalRequest rentalRequest);

    RentalRequest toDto(Rental rental);

    @Mapping(target = "username", source = "user.username")
    @Mapping(target = "movieName", source = "movie.name")
    RentalResponse toResponse(Rental rental);

    List<RentalResponse> toResponse(List<Rental> rentalList);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Rental partialUpdate(RentalRequest rentalRequest, @MappingTarget Rental rental);
}