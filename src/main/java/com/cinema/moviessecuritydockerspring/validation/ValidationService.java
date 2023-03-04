package com.cinema.moviessecuritydockerspring.validation;

import com.cinema.moviessecuritydockerspring.domain.category.Category;
import com.cinema.moviessecuritydockerspring.domain.category.CategoryRepository;
import com.cinema.moviessecuritydockerspring.domain.role.Role;
import com.cinema.moviessecuritydockerspring.domain.role.RoleRepository;
import com.cinema.moviessecuritydockerspring.domain.user.User;
import com.cinema.moviessecuritydockerspring.domain.user.UserRepository;
import com.cinema.moviessecuritydockerspring.infrastructure.exception.DataAlreadyExistsException;
import com.cinema.moviessecuritydockerspring.infrastructure.exception.DataNotFoundException;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ValidationService {

    @Resource
    private RoleRepository roleRepository;

    @Resource
    private UserRepository userRepository;

    @Resource
    private CategoryRepository categoryRepository;


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

    /**
     * Checks whether the category already exists in the database.
     */
    public String categoryExists(String name) {
        Category category = categoryRepository.findByName(name);
        if (category == null) {
            return "New category '" + name + "' is added!";
        } else {
            String message = "Category name '" + name + "' already exists";
            throw new DataAlreadyExistsException(message);
        }
    }

    /**
     * Checks whether there are categories in the database to return.
     */
    public String categoriesNotFound() {
        List<Category> categoryList = categoryRepository.findAll();
        if (categoryList.size() != 0) {
            return "Request completed!";
        } else {
            String message = "No categories found!";
            throw new DataNotFoundException(message);
        }
    }

    /**
     * Checks whether there is the requested category with inserted name in the database.
     */
    public String categoryNotFound(String name) {
        Category category = categoryRepository.findByName(name);
        if (category != null) {
            return "Category is found!";
        } else {
            String message = "No category with name '" + name + "' exists!";
            throw new DataNotFoundException(message);
        }
    }

    /**
     * Checks whether there is the requested category with inserted id in the database.
     */
    public String categoryNotFound(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isPresent()) {
            return "Category is found!";
        } else {
            String message = "No category with ID '" + id + "' exists!";
            throw new DataNotFoundException(message);
        }
    }
}
