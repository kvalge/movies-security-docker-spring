package com.cinema.moviessecuritydockerspring.domain.user;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Resource
    private UserRepository userRepository;

    @Resource
    private UserMapper userMapper;

    public List<UserResponse> getAllUsers() {
        List<User> users = userRepository.findAll();

        return userMapper.toResponse(users);
    }
}
