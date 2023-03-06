package com.cinema.moviessecuritydockerspring.domain.moviedetails;

import com.cinema.moviessecuritydockerspring.domain.category.Category;
import com.cinema.moviessecuritydockerspring.domain.movie.Movie;
import com.cinema.moviessecuritydockerspring.domain.movie.MovieRepository;
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
class MovieDetailsServiceTest {

    @InjectMocks
    private MovieDetailsService movieDetailsService;

    @Mock
    private MovieDetailsRepository movieDetailsRepository;

    @Mock
    private MovieDetailsMapper movieDetailsMapper;

    @Mock
    private MovieRepository movieRepository;

    @Mock
    private ValidationService validationService;

    Movie movieEntity = new Movie();

    @BeforeEach
    void setup() {

    }

    @Test
    void addDetails() {
        Category categoryEntity = new Category();
        categoryEntity.setName("Kategooria");

        movieEntity.setName("Film");
        movieEntity.setCategory(categoryEntity);

        MovieDetailsRequest request = new MovieDetailsRequest();
        request.setDirector("Rezhisöör");
        request.setWriter("Autor");
        request.setStars("Näitlejad");
        request.setYear("2000");
        request.setCountry("Tootjamaa");
        request.setDescription("Kirjeldus");
        request.setMovieName(movieEntity.getName());

        MovieDetails details = new MovieDetails();
        details.setDirector("Rezhisöör");
        details.setWriter("Autor");
        details.setStars("Näitlejad");
        details.setYear("2000");
        details.setCountry("Tootjamaa");
        details.setDescription("Kirjeldus");
        details.setMovie(movieEntity);

        when(validationService.movieExists(request.getMovieName())).thenReturn(null);
        when(movieDetailsMapper.toEntity(request)).thenReturn(details);
        when(movieRepository.findByName(request.getMovieName())).thenReturn(movieEntity);
        when(movieDetailsRepository.save(Mockito.any(MovieDetails.class))).thenReturn(details);
        when(movieDetailsMapper.toDto(details)).thenReturn(request);

        MovieDetailsRequest addedDetails = movieDetailsService.addDetails(request);

        assertNotNull(addedDetails);
        assertEquals(request.getMovieName(), addedDetails.getMovieName());
    }

    @Test
    void updateDetails() {
    }

    @Test
    void deleteDetails() {
    }
}