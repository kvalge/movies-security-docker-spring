package com.cinema.moviessecuritydockerspring.validation;

import com.cinema.moviessecuritydockerspring.domain.role.Role;
import com.cinema.moviessecuritydockerspring.domain.role.RoleRepository;
import com.cinema.moviessecuritydockerspring.infrastructure.exception.DataAlreadyExistsException;
import com.cinema.moviessecuritydockerspring.infrastructure.exception.DataNotFoundException;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ValidationService {

    @Resource
    private RoleRepository roleRepository;


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
     * Checks whether there are roles in the database to return.
     */
    public String rolesNotFound() {
        List<Role> roleList = roleRepository.findAll();
        if (roleList.size() != 0) {
            return "Request completed!";
        } else {
            String message = "No roles found!";
            throw new DataNotFoundException(message);
        }
    }
}
