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
        validationService.usersNotFound();

        List<User> users = userRepository.findAll();

        return userMapper.toResponse(users);
    }

    /**
     * Checks is there a requested user in database before finding user by name.
     */
    public UserResponse getUserByUsername(String username) {
        validationService.userNotFound(username);

        User user = userRepository.findByUsername(username);
        return userMapper.toResponse(user);
    }

    /**
     * Checks is there a requested user in database before finding user by name for deletion.
     */
    public void deleteUserByUsername(String username) {
        validationService.userNotFound(username);

        User user = userRepository.findByUsername(username);

        userRepository.delete(user);
    }
}
