package com.cinema.moviessecuritydockerspring.domain.movie;

import com.cinema.moviessecuritydockerspring.domain.category.Category;
import com.cinema.moviessecuritydockerspring.domain.category.CategoryRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

    @Resource
    MovieRepository movieRepository;
    @Resource
    private MovieMapper movieMapper;

    @Resource
    private CategoryRepository categoryRepository;

    public void addNewMovie(MovieRequest request) {
        Movie movie = movieMapper.toEntity(request);

        Movie newMovie = new Movie();
        newMovie.setName(movie.getName());
        newMovie.setDescription(movie.getDescription());
        Category category = categoryRepository.findByName(request.getCategoryName());
        newMovie.setCategory(category);

        movieRepository.save(newMovie);
    }

    public MovieRequest getByName(String name) {
        Movie movie = movieRepository.findByName(name);

        return movieMapper.toRequest(movie);
    }
}
