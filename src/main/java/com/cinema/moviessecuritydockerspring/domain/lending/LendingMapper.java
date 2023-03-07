package com.cinema.moviessecuritydockerspring.domain.lending;

import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface LendingMapper {

    @Mapping(target = "user.username", source = "username")
    @Mapping(target = "movie.name", source = "movieName")
    Lending toEntity(LendingRequest lendingRequest);

    LendingRequest toDto(Lending lending);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Lending partialUpdate(LendingRequest lendingRequest, @MappingTarget Lending lending);
}