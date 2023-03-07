package com.cinema.moviessecuritydockerspring.domain.rental;

import com.cinema.moviessecuritydockerspring.domain.movie.Movie;
import com.cinema.moviessecuritydockerspring.domain.movie.MovieRepository;
import com.cinema.moviessecuritydockerspring.domain.user.User;
import com.cinema.moviessecuritydockerspring.domain.user.UserRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class RentalService {

    @Resource
    private RentalMapper rentalMapper;

    @Resource
    private RentalRepository rentalRepository;

    @Resource
    private UserRepository userRepository;

    @Resource
    private MovieRepository movieRepository;

    public void addLending(RentalRequest request) {
        Rental rental = rentalMapper.toEntity(request);

        Rental newRental = new Rental();
        User user = userRepository.findByUsername(rental.getUser().getUsername());
        newRental.setUser(user);

        Movie movie = movieRepository.findByName(rental.getMovie().getName());
        newRental.setMovie(movie);

        LocalDate rentalDate = rental.getRentalDate();
        newRental.setRentalDate(rentalDate);

        newRental.setDueDate(rentalDate.plusDays(2));

        rentalRepository.save(newRental);
    }

    public List<RentalResponse> getRentalsByUsername(String username) {
        List<Rental> rentalList = rentalRepository.findByUsername(username);

        return rentalMapper.toResponse(rentalList);
    }
}
