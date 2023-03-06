package com.cinema.moviessecuritydockerspring.domain.moviedetails;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/details")
public class MovieDetailsController {

    @Resource
    private MovieDetailsService movieDetailsService;

    @PostMapping("/new")
    public void addDetails(@RequestBody MovieDetailsRequest request) {
        movieDetailsService.addDetails(request);
    }

    @PutMapping
    public void updateDetails(@RequestBody MovieDetailsRequest request) {
        movieDetailsService.updateDetails(request);
    }

    @DeleteMapping("/name")
    public void deleteDetails(@RequestParam String name) {
        movieDetailsService.deleteDetails(name);
    }
}
