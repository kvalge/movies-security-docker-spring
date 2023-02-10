package com.cinema.moviessecuritydockerspring.login;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
@CrossOrigin
public class LoginController {

    @Resource
    private LoginService loginService;

    @PostMapping
    public void login(@RequestBody LoginRequest request) {
        loginService.login(request);
    }
}
