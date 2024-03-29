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

    @GetMapping("/id")
    public MovieDetailsResponse getDetailsById(@RequestParam Long id) {
        return movieDetailsService.getDetailsById(id);
    }

    @GetMapping("/movie")
    public MovieDetailsResponse getDetailsByMovieId(@RequestParam Long id) {
        return movieDetailsService.getDetailsByMovieId(id);
    }

    @GetMapping("/name")
    public MovieDetailsResponse getDetailsByMovieName(@RequestParam String movieName) {
        return movieDetailsService.getDetailsByMovieName(movieName);
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
