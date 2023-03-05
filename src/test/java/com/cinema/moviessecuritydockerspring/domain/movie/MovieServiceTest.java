package com.cinema.moviessecuritydockerspring.domain.movie;

import com.cinema.moviessecuritydockerspring.domain.category.Category;
import com.cinema.moviessecuritydockerspring.validation.ValidationService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
    private ValidationService validationService;

    @Test
    void addNewMovie() {
    }

    /**
     * Tests equality between the name, the description and the category name of the hard coded movie
     * entity and the movie properties returned via getByName method.
     */
    @Test
    void getByName() {
        Category category = new Category();
        category.setName("Kategooria");

        Movie movie = new Movie();
        movie.setName("Film");
        movie.setDescription("Kirjeldus");
        movie.setCategory(category);

        MovieRequest request = new MovieRequest();
        request.setName("Film");
        request.setDescription("Kirjeldus");
        request.setCategoryName(category.getName());

        when(movieRepository.findByName("Film")).thenReturn(movie);
        when(movieMapper.toRequest(movie)).thenReturn(request);
        when(validationService.movieNotFound("Film")).thenReturn("Film");

        String name = movieService.getByName("Film").getName();
        String description = movieService.getByName("Film").getDescription();
        String categoryName = movieService.getByName("Film").getCategoryName();

        String movieName = movie.getName();
        String movieDescription = movie.getDescription();
        String movieCategory = movie.getCategory().getName();

        assertEquals(movieName, name);
        assertEquals(movieDescription, description);
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