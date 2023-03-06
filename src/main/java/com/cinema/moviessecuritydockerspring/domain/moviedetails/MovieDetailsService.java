package com.cinema.moviessecuritydockerspring.domain.moviedetails;

import com.cinema.moviessecuritydockerspring.domain.movie.Movie;
import com.cinema.moviessecuritydockerspring.domain.movie.MovieRepository;
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

    public void addDetails(MovieDetailsRequest request) {
        MovieDetails details = movieDetailsMapper.toEntity(request);
        MovieDetails newDetails = new MovieDetails();
        newDetails.setDirector(details.getDirector());
        newDetails.setWriter(details.getWriter());
        newDetails.setStars(details.getStars());
        newDetails.setYear(details.getStars());
        newDetails.setCountry(details.getCountry());
        newDetails.setDescription(details.getDescription());

        Movie movie = movieRepository.findByName(request.getMovieName());
        newDetails.setMovie(movie);

        movieDetailsRepository.save(newDetails);
    }

    public void updateDetails(MovieDetailsRequest request) {
        MovieDetails movie = movieDetailsRepository.findByMovieName(request.getMovieName());
        MovieDetails updatedMovie = movieDetailsMapper.partialUpdate(request, movie);

        movieDetailsRepository.save(updatedMovie);
    }
}
