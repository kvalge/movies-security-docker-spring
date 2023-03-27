package com.cinema.moviessecuritydockerspring.domain.moviedetails;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/details")
public class MovieDetailsController {

    @Resource
    private MovieDetailsService movieDetailsService;

    @PostMapping("/new")
    public MovieDetailsRequest addDetails(@RequestBody MovieDetailsRequest request) {
        return movieDetailsService.addDetails(request);
    }

    @GetMapping("/all")
    public List<MovieDetailsResponse> getAllDetails() {
        return movieDetailsService.getAllDetails();
    }

    @GetMapping("/{id}")
    public MovieDetailsResponse getDetailsByMovieId(@PathVariable Long id) {
        return movieDetailsService.getDetailsByMovieId(id);
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
