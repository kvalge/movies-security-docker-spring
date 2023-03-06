package com.cinema.moviessecuritydockerspring.domain.movie;

import com.cinema.moviessecuritydockerspring.domain.category.Category;
import com.cinema.moviessecuritydockerspring.domain.category.CategoryRepository;
import com.cinema.moviessecuritydockerspring.validation.ValidationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest
class MovieServiceTest {

    @InjectMocks
    private MovieService movieService;

    @Mock
    private MovieRepository movieRepository;

    @Mock
    private MovieMapper movieMapper;

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private ValidationService validationService;

    Movie movie = new Movie();

    Category category = new Category();

    @BeforeEach
    void setup(){
        category.setName("Kategooria");

        movie.setName("Film");
        movie.setCategory(category);

        MovieRequest request = new MovieRequest();
        request.setName("Film");
        request.setCategoryName(category.getName());

        when(movieRepository.findByName("Film")).thenReturn(movie);
        when(movieMapper.toRequest(movie)).thenReturn(request);
        when(validationService.movieNotFound("Film")).thenReturn("Film");
    }

    /**
     * Tests whether addNewMovie will not return null and equality between the hard coded movie name and
     * the movie name returned by addNewMovie method.
     */
    @Test
    void addNewMovie() {
        Movie newMovie = new Movie();
        newMovie.setName("Muuvi");
        newMovie.setCategory(category);

        MovieRequest request = new MovieRequest();
        request.setName("Muuvi");
        request.setCategoryName(category.getName());

        Movie mapped = new Movie();
        mapped.setName(request.getName());
        mapped.setCategory(category);

        when(movieMapper.toEntity(request)).thenReturn(mapped);
        when(movieRepository.save(Mockito.any(Movie.class))).thenReturn(newMovie);
        when(categoryRepository.findByName(request.getCategoryName())).thenReturn(category);
        when(movieMapper.toRequest(newMovie)).thenReturn(request);

        MovieRequest addedNewMovie = movieService.addNewMovie(request);

        assertNotNull(addedNewMovie);
        assertEquals(newMovie.getName(), addedNewMovie.getName());
    }

    /**
     * Tests equality between the name, the description and the category name of the hard coded movie
     * entity and the movie properties returned via getByName method.
     */
    @Test
    void getByName() {
        String name = movieService.getByName(movie.getName()).getName();
        String categoryName = movieService.getByName(movie.getName()).getCategoryName();

        String movieName = movie.getName();
        String movieCategory = movie.getCategory().getName();

        assertEquals(movieName, name);
        assertEquals(movieCategory, categoryName);
    }

    @Test
    void getAll() {
    }

    @Test
    void updateMovie() {
    }

    @Test
    void deleteByName() {
    }
}