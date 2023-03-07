package com.cinema.moviessecuritydockerspring.domain.lending;

import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface LendingMapper {
    Lending toEntity(LendingRequest lendingRequest);

    LendingRequest toDto(Lending lending);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Lending partialUpdate(LendingRequest lendingRequest, @MappingTarget Lending lending);
}