package com.cinema.moviessecuritydockerspring.domain.user;

import jakarta.annotation.Resource;
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

    @GetMapping("/name")
    public UserResponse getUserByUsername(@RequestParam String username) {
        return userService.getUserByUsername(username);
    }
}
