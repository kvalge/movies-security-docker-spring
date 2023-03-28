package com.cinema.moviessecuritydockerspring.domain.moviedetails;

import com.cinema.moviessecuritydockerspring.domain.movie.Movie;
import com.cinema.moviessecuritydockerspring.domain.movie.MovieRepository;
import com.cinema.moviessecuritydockerspring.domain.price.PriceService;
import com.cinema.moviessecuritydockerspring.validation.ValidationService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieDetailsService {

    @Resource
    private MovieDetailsMapper movieDetailsMapper;

    @Resource
    private MovieDetailsRepository movieDetailsRepository;

    @Resource
    private MovieRepository movieRepository;

    @Resource
    private PriceService priceService;

    @Resource
    private ValidationService validationService;

    /**
     * Checks whether the movie exists to add details to it.
     */
    public MovieDetailsRequest addDetails(MovieDetailsRequest request) {
        validationService.movieNotFound(request.getMovieName());

        MovieDetails details = movieDetailsMapper.toEntity(request);
        MovieDetails newDetails = new MovieDetails();
        newDetails.setDirector(details.getDirector());
        newDetails.setWriter(details.getWriter());
        newDetails.setStars(details.getStars());
        newDetails.setYear(details.getYear());
        newDetails.setCountry(details.getCountry());
        newDetails.setDescription(details.getDescription());
        newDetails.setImage(details.getImage());

        Movie movie = movieRepository.findByName(request.getMovieName());
        newDetails.setMovie(movie);

        MovieDetails savedDetails = movieDetailsRepository.save(newDetails);
        savedDetails.setPrice(priceService.setPrice(request.getMovieName()));
        MovieDetails movieDetails = movieDetailsRepository.save(savedDetails);

        return movieDetailsMapper.toDto(movieDetails);
    }


/*    public MovieDetailsResponse getDetailsById(Long movieId) {
        MovieDetails movieDetails = movieDetailsRepository.findByMovieId(movieId);

        return movieDetailsMapper.toResponse(movieDetails);
    }*/

    public MovieDetailsResponse getDetailsById(Long id) {
        List<MovieDetails> movieDetails = movieDetailsRepository.findAll();

        MovieDetailsResponse response = null;
        for (MovieDetails details : movieDetails) {
            if (details.getId() == id) {

                response = movieDetailsMapper.toResponse(details);
            }
        }
        return response;
    }

    /**
     * Checks whether the movie exists to update of its details.
     */
    public void updateDetails(MovieDetailsRequest request) {
        validationService.movieNotFound(request.getMovieName());

        MovieDetails movie = movieDetailsRepository.findByMovieName(request.getMovieName());
        MovieDetails updatedMovie = movieDetailsMapper.partialUpdate(request, movie);
        updatedMovie.setPrice(priceService.setPrice(request.getMovieName()));

        movieDetailsRepository.save(updatedMovie);
    }

    /**
     * Checks whether the movie exists to delete its details.
     */
    public void deleteDetails(String name) {
        validationService.movieNotFound(name);

        MovieDetails details = movieDetailsRepository.findByMovieName(name);

        movieDetailsRepository.delete(details);
    }

    public List<MovieDetailsResponse> getAllDetails() {
        List<MovieDetails> movieDetails = movieDetailsRepository.findAll();

        return movieDetailsMapper.toResponse(movieDetails);
    }
}
