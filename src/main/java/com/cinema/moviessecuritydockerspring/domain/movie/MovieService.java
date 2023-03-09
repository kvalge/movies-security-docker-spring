package com.cinema.moviessecuritydockerspring.domain.movie;

import com.cinema.moviessecuritydockerspring.domain.category.Category;
import com.cinema.moviessecuritydockerspring.domain.category.CategoryRepository;
import com.cinema.moviessecuritydockerspring.domain.moviedetails.MovieDetailsService;
import com.cinema.moviessecuritydockerspring.domain.rental.Rental;
import com.cinema.moviessecuritydockerspring.domain.rental.RentalRepository;
import com.cinema.moviessecuritydockerspring.validation.ValidationService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.List;

@Service
public class MovieService {

    @Resource
    MovieRepository movieRepository;

    @Resource
    private MovieMapper movieMapper;

    @Resource
    private CategoryRepository categoryRepository;

    @Resource
    private MovieDetailsService movieDetailsService;

    @Resource
    private RentalRepository rentalRepository;

    @Resource
    private ValidationService validationService;

    /**
     * Checks isn't the movie already in the database before adding it as new to the database.
     */
    public MovieRequest addNewMovie(MovieRequest request) {
        validationService.movieExists(request.getName());

        Movie movie = movieMapper.toEntity(request);

        Movie newMovie = new Movie();
        newMovie.setName(movie.getName());
        Category category = categoryRepository.findByName(request.getCategoryName());
        newMovie.setCategory(category);

        Movie savedNewMovie = movieRepository.save(newMovie);
        return movieMapper.toRequest(savedNewMovie);
    }

    /**
     * Checks is there a requested movie in the database before returning it.
     * Adds average rating to movie info.
     * In case of user hasn't inserted a rating to the rented movie this rental will be left out of
     * the average rating calculation.
     */
    public MovieResponse getByName(String name) {
        validationService.movieNotFound(name);

        Movie movie = movieRepository.findByName(name);

        MovieResponse toResponse = movieMapper.toResponse(movie);

        MovieResponse response = new MovieResponse();
        response.setMovieName(toResponse.getMovieName());
        response.setCategoryName(toResponse.getCategoryName());

        int ratings = 0;
        int numberOfRentals = 0;
        List<Rental> rentals = rentalRepository.findByMovieName(name);
        for (Rental rental : rentals) {
            Integer rating = rental.getRating();
            if (rating == null) {
                continue;
            }
            ratings += rating;
            numberOfRentals++;
        }

        DecimalFormat decimalFormat = new DecimalFormat();
        decimalFormat.setMaximumFractionDigits(1);
        response.setAvRating(Float.valueOf(decimalFormat.format((float) ratings / numberOfRentals)));

        return response;
    }

    /**
     * Checks isn't the movie database empty before returning all movies.
     */
    public List<MovieRequest> getAll() {
        validationService.moviesNotFound();

        List<Movie> movies = movieRepository.findAll();
        return movieMapper.toRequest(movies);
    }

    /**
     * Checks is there a requested movie in the database before updating it.
     */
    public void updateMovie(MovieRequest request) {
        validationService.movieNotFound(request.getName());

        Movie movie = movieRepository.findByName(request.getName());

        Movie updatedMovie = movieMapper.update(request, movie);
        Category category = categoryRepository.findByName(request.getCategoryName());
        updatedMovie.setCategory(category);

        movieRepository.save(updatedMovie);
    }

    /**
     * Checks is there a requested movie in the database before deleting it.
     * Deletes also movie details.
     */
    public void deleteByName(String name) {
        validationService.movieNotFound(name);

        Movie movie = movieRepository.findByName(name);

        movieDetailsService.deleteDetails(name);

        movieRepository.delete(movie);
    }
}
