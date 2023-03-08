package com.cinema.moviessecuritydockerspring.domain.movie;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movie")
@CrossOrigin
public class MovieController {

    @Resource
    private MovieService movieService;

    @PostMapping("/new")
    public MovieRequest addNewMovie(@RequestBody MovieRequest request) {
        return movieService.addNewMovie(request);
    }

    @GetMapping("/name")
    public MovieResponse getByName(@RequestParam String name) {
        return movieService.getByName(name);
    }

    @GetMapping
    public List<MovieRequest> getAll() {
        return movieService.getAll();
    }

    @PutMapping
    public void updateMovie(@RequestBody MovieRequest request) {
        movieService.updateMovie(request);
    }

    @DeleteMapping("/name")
    public void deleteByName(@RequestParam String name) {
        movieService.deleteByName(name);
    }
}
