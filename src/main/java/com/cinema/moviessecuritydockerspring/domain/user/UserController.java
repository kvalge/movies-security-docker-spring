package com.cinema.moviessecuritydockerspring.domain.user;

import jakarta.annotation.Resource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping
    public List<UserResponse> getAllUsers() {
        return userService.getAllUsers();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/name")
    public UserResponse getUserByUsername(@RequestParam String username) {
        return userService.getUserByUsername(username);
    }

    @DeleteMapping("/name")
    public void deleteUserByUsername(@RequestParam String username) {
        userService.deleteUserByUsername(username);
    }
}
