package com.cinema.moviessecuritydockerspring.domain.role;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface RoleMapper {

    Role toEntity(String name);
}
