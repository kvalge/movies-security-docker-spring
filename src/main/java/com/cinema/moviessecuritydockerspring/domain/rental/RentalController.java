package com.cinema.moviessecuritydockerspring.domain.rental;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("rental")
public class RentalController {

    @Resource
    private RentalService rentalService;

    @PostMapping("/new")
    public void addRental(@RequestBody RentalRequest request) {
        rentalService.addLending(request);
    }

    @GetMapping("/name")
    public RentalResponse getLendingsByUsername(@RequestParam String username) {
return null;
    }
}
