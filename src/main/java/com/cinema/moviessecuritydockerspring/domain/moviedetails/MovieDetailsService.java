package com.cinema.moviessecuritydockerspring.domain.moviedetails;

import com.cinema.moviessecuritydockerspring.domain.movie.Movie;
import com.cinema.moviessecuritydockerspring.domain.movie.MovieRepository;
import com.cinema.moviessecuritydockerspring.validation.ValidationService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class MovieDetailsService {

    @Resource
    private MovieDetailsMapper movieDetailsMapper;

    @Resource
    private MovieDetailsRepository movieDetailsRepository;

    @Resource
    private MovieRepository movieRepository;

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

        Movie movie = movieRepository.findByName(request.getMovieName());
        newDetails.setMovie(movie);

        MovieDetails savedDetails = movieDetailsRepository.save(newDetails);

        return movieDetailsMapper.toDto(savedDetails);
    }

    /**
     * Checks whether the movie exists to update of its details.
     */
    public void updateDetails(MovieDetailsRequest request) {
        validationService.movieNotFound(request.getMovieName());

        MovieDetails movie = movieDetailsRepository.findByMovieName(request.getMovieName());
        MovieDetails updatedMovie = movieDetailsMapper.partialUpdate(request, movie);

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
}
