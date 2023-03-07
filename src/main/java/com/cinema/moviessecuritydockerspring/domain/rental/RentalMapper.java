package com.cinema.moviessecuritydockerspring.domain.rental;

import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface RentalMapper {
    @Mapping(target = "user.username", source = "username")
    @Mapping(target = "movie.name", source = "movieName")
    Rental toEntity(RentalRequest rentalRequest);

    RentalRequest toDto(Rental rental);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Rental partialUpdate(RentalRequest rentalRequest, @MappingTarget Rental rental);
}