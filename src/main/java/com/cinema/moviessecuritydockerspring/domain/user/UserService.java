package com.cinema.moviessecuritydockerspring.domain.user;

import com.cinema.moviessecuritydockerspring.validation.ValidationService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Resource
    private UserRepository userRepository;

    @Resource
    private UserMapper userMapper;

    @Resource
    private ValidationService validationService;

    /**
     * Checks isn't the user database empty before returning all users.
     */
    public List<UserResponse> getAllUsers() {
        validationService.libraryUsersNotFound();

        List<User> users = userRepository.findAll();

        return userMapper.toResponse(users);
    }

    public UserResponse getUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
        return userMapper.toResponse(user);
    }

    public void deleteUserByUsername(String username) {
        User user = userRepository.findByUsername(username);

        userRepository.delete(user);
    }
}
