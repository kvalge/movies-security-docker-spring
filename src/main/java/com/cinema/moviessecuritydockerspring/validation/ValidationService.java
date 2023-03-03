package com.cinema.moviessecuritydockerspring.validation;

import com.cinema.moviessecuritydockerspring.domain.role.Role;
import com.cinema.moviessecuritydockerspring.domain.role.RoleRepository;
import com.cinema.moviessecuritydockerspring.infrastructure.exception.DataAlreadyExistsException;
import com.cinema.moviessecuritydockerspring.infrastructure.exception.DataNotFoundException;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

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
}
