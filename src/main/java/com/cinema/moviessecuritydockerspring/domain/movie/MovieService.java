package com.cinema.moviessecuritydockerspring.domain.movie;

import com.cinema.moviessecuritydockerspring.domain.category.Category;
import com.cinema.moviessecuritydockerspring.domain.category.CategoryRepository;
import com.cinema.moviessecuritydockerspring.validation.ValidationService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

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
    private ValidationService validationService;

    /**
     * Checks isn't the movie already in the database before adding it as new to the database.
     */
    public void addNewMovie(MovieRequest request) {
        validationService.movieExists(request.getName());

        Movie movie = movieMapper.toEntity(request);

        Movie newMovie = new Movie();
        newMovie.setName(movie.getName());
        newMovie.setDescription(movie.getDescription());
        Category category = categoryRepository.findByName(request.getCategoryName());
        newMovie.setCategory(category);

        movieRepository.save(newMovie);
    }

    /**
     * Checks is there a requested movie in the database before returning it.
     */
    public MovieRequest getByName(String name) {
        validationService.movieNotFound(name);

        Movie movie = movieRepository.findByName(name);

        return movieMapper.toRequest(movie);
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
     */
    public void deleteByName(String name) {
        validationService.movieNotFound(name);

        Movie movie = movieRepository.findByName(name);
        movieRepository.delete(movie);
    }
}
