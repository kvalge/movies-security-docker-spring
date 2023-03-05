package com.cinema.moviessecuritydockerspring.domain.moviedetails;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/details")
public class MovieDetailsController {

    @Resource
    private MovieDetailsService movieDetailsService;

    @PostMapping("/new")
    public void addDetails(@RequestBody MovieDetailsRequest request) {
        movieDetailsService.addDetails(request);
    }
}
