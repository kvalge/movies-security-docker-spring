package com.cinema.moviessecuritydockerspring.domain.movie;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public List<MovieRequest> getAll() {
        return movieService.getAll();
    }

    @PutMapping("/name")
    public void updateMovie(@RequestBody MovieRequest request) {
        movieService.updateMovie(request);
    }
}
