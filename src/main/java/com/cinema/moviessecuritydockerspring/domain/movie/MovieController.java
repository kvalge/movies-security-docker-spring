package com.cinema.moviessecuritydockerspring.domain.movie;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movie")
public class MovieController {

    @Resource
    private MovieService movieService;

    @PostMapping("/new")
    public void addNewMovie(@RequestBody MovieRequest request) {
        movieService.addNewMovie(request);
    }

    @GetMapping("/name")
    public MovieRequest getByName(@RequestParam String name) {
        return movieService.getByName(name);
    }
}
