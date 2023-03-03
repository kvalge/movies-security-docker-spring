package com.cinema.moviessecuritydockerspring.validation;

import com.cinema.moviessecuritydockerspring.domain.role.Role;
import com.cinema.moviessecuritydockerspring.domain.role.RoleRepository;
import com.cinema.moviessecuritydockerspring.domain.user.User;
import com.cinema.moviessecuritydockerspring.domain.user.UserRepository;
import com.cinema.moviessecuritydockerspring.infrastructure.exception.DataAlreadyExistsException;
import com.cinema.moviessecuritydockerspring.infrastructure.exception.DataNotFoundException;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ValidationService {

    @Resource
    private RoleRepository roleRepository;

    @Resource
    private UserRepository userRepository;


    /**
     * Checks whether the role already exists in the database.
     */
    public String roleExists(String name) {
        Role role = roleRepository.findByName(name);
        if (role == null) {
            return "New role '" + name + "' is added!";
        } else {
            String message = "Role name '" + name + "' already exists";
            throw new DataAlreadyExistsException(message);
        }
    }

    /**
     * Checks whether there is the requested role in the database.
     */
    public String roleNotFound(String name) {
        Role role = roleRepository.findByName(name);
        if (role != null) {
            return "Role is found!";
        } else {
            String message = "No such role exists!";
            throw new DataNotFoundException(message);
        }
    }

    /**
     * Checks whether there are users in the database to return.
     */
    public String libraryUsersNotFound() {
        List<User> libraryUserList = userRepository.findAll();
        if (libraryUserList.size() != 0) {
            return "Request completed!";
        } else {
            String message = "No user found!";
            throw new DataNotFoundException(message);
        }
    }

    /**
     * Checks whether there is the requested user in the database.
     */
    public String userNotFound(String username) {
        User libraryUser = userRepository.findByUsername(username);
        if (libraryUser != null) {
            return "User is found!";
        } else {
            String message = "No user with username '" + username + "' exists!";
            throw new DataNotFoundException(message);
        }
    }
}
