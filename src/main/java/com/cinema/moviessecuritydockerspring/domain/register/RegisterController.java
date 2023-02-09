package com.cinema.moviessecuritydockerspring.domain.register;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/register")
@CrossOrigin
public class RegisterController {

    @Resource
    private RegisterService registerService;

    @PostMapping
    public void register(@RequestBody RegisterRequest request) {
        registerService.register(request);
    }
}
