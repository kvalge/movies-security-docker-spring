package com.cinema.moviessecuritydockerspring.domain.user;

import com.cinema.moviessecuritydockerspring.domain.register.RegisterRequest;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface UserMapper {

    UserResponse toResponse(User user);

    List<UserResponse> toResponse(List<User> users);

    User toEntity(RegisterRequest request);
}
