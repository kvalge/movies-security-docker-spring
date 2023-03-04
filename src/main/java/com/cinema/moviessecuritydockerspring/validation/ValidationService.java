package com.cinema.moviessecuritydockerspring.validation;

import com.cinema.moviessecuritydockerspring.domain.category.Category;
import com.cinema.moviessecuritydockerspring.domain.category.CategoryRepository;
import com.cinema.moviessecuritydockerspring.domain.movie.Movie;
import com.cinema.moviessecuritydockerspring.domain.movie.MovieRepository;
import com.cinema.moviessecuritydockerspring.domain.role.Role;
import com.cinema.moviessecuritydockerspring.domain.role.RoleRepository;
import com.cinema.moviessecuritydockerspring.domain.user.User;
import com.cinema.moviessecuritydockerspring.domain.user.UserRepository;
import com.cinema.moviessecuritydockerspring.infrastructure.exception.DataExistsException;
import com.cinema.moviessecuritydockerspring.infrastructure.exception.DataNotFoundException;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ValidationService {

    public static final String REQUEST_COMPLETED = "Request completed!";

    @Resource
    private RoleRepository roleRepository;

    @Resource
    private UserRepository userRepository;

    @Resource
    private CategoryRepository categoryRepository;

    @Resource
    private MovieRepository movieRepository;


    /**
     * Checks whether the role already exists in the database.
     */
    public String roleExists(String name) {
        Role role = roleRepository.findByName(name);
        if (role == null) {
            return REQUEST_COMPLETED;
        } else {
            String message = "Role name '" + name + "' already exists!";
            throw new DataExistsException(message);
        }
    }

    /**
     * Checks whether there is the requested role in the database.
     */
    public String roleNotFound(String name) {
        Role role = roleRepository.findByName(name);
        if (role != null) {
            return REQUEST_COMPLETED;
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
            return REQUEST_COMPLETED;
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
            return REQUEST_COMPLETED;
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
            return REQUEST_COMPLETED;
        } else {
            String message = "Category name '" + name + "' already exists!";
            throw new DataExistsException(message);
        }
    }

    /**
     * Checks whether there are categories in the database to return.
     */
    public String categoriesNotFound() {
        List<Category> categoryList = categoryRepository.findAll();
        if (categoryList.size() != 0) {
            return REQUEST_COMPLETED;
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
            return REQUEST_COMPLETED;
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
            return REQUEST_COMPLETED;
        } else {
            String message = "No category with ID '" + id + "' exists!";
            throw new DataNotFoundException(message);
        }
    }

    public String categoryIsInUse(String name) {
        return getMessage(name);
    }

    public String categoryIsInUse(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        String name = category.get().getName();

        return getMessage(name);
    }

    private String getMessage(String name) {
        List<Movie> movies = getMovies();
        for (Movie movie : movies) {
            String categoryName = movie.getCategory().getName();
            if (categoryName.equals(name)) {
                String message = "Category '" + name + "' is in use, deletion is not allowed!";
                throw new DataExistsException(message);
            }
        }
        return REQUEST_COMPLETED;
    }

    private List<Movie> getMovies() {
        return movieRepository.findAll();
    }
}
