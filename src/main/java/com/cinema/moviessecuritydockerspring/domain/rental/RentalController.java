package com.cinema.moviessecuritydockerspring.domain.rental;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("rental")
public class RentalController {

    @Resource
    private RentalService rentalService;

    @PostMapping("/new")
    public void addRental(@RequestBody RentalRequest request) {
        rentalService.addLending(request);
    }

    @GetMapping("/username")
    public List<RentalResponse> getRentalsByUsername(@RequestParam String username) {
        return rentalService.getRentalsByUsername(username);
    }

    @GetMapping("/movieName")
    public List<RentalResponse> getRentalsByMovieName(@RequestParam String movieName) {
        return rentalService.getRentalsByMovieName(movieName);
    }

    @PutMapping
    public RentalResponse updateRating(@RequestParam String username,
                                       @RequestParam String movieName,
                                       @RequestParam String rating) {
        return rentalService.updateRating(username, movieName, rating);
    }
}
