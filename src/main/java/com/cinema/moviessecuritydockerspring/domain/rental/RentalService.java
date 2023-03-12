package com.cinema.moviessecuritydockerspring.domain.rental;

import com.cinema.moviessecuritydockerspring.domain.movie.Movie;
import com.cinema.moviessecuritydockerspring.domain.movie.MovieRepository;
import com.cinema.moviessecuritydockerspring.domain.user.User;
import com.cinema.moviessecuritydockerspring.domain.user.UserRepository;
import com.cinema.moviessecuritydockerspring.validation.ValidationService;
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

    @Resource
    private ValidationService validationService;

    /**
     * Checks whether the user and the movie exists before adding to user the new rental of the requested movie.
     */
    public void addRental(RentalRequest request) {
        validationService.userNotFound(request.getUsername());
        validationService.movieNotFound(request.getMovieName());

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

    public List<RentalResponse> getRentalsByMovieName(String movieName) {
        List<Rental> rentalList = rentalRepository.findByMovieName(movieName);

        return rentalMapper.toResponse(rentalList);
    }

    public RentalResponse updateRating(String username, String movieName, Integer rating) {
        Rental rental = rentalRepository.findByUsernameAndMovieName(username, movieName);
        Rental updatedRental = rentalMapper.updateRating(rating, rental);
        rentalRepository.save(updatedRental);

        return rentalMapper.toResponse(updatedRental);
    }
}
