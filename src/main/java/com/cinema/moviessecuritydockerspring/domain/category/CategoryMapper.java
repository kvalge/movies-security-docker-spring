package com.cinema.moviessecuritydockerspring.domain.category;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface CategoryMapper {

    Category toEntity(String name);

    List<CategoryResponse> toResponse(List<Category> categories);
}
