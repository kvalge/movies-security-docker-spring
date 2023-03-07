package com.cinema.moviessecuritydockerspring.domain.lending;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("lending")
public class LendingController {

    @Resource
    private LendingService lendingService;

    @PostMapping("/new")
    public void addLending(@RequestBody LendingRequest request) {
        lendingService.addLending(request);
    }
}
