package com.cinema.moviessecuritydockerspring.domain.moviedetails;

import com.cinema.moviessecuritydockerspring.domain.category.Category;
import com.cinema.moviessecuritydockerspring.domain.movie.Movie;
import com.cinema.moviessecuritydockerspring.domain.movie.MovieRepository;
import com.cinema.moviessecuritydockerspring.domain.price.PriceService;
import com.cinema.moviessecuritydockerspring.validation.ValidationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

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
    private PriceService priceService;

    @Mock
    private ValidationService validationService;

    Movie movieEntity = new Movie();
    MovieDetailsRequest request = new MovieDetailsRequest();
    MovieDetails details = new MovieDetails();

    @BeforeEach
    void setup() {
        Category categoryEntity = new Category();
        categoryEntity.setName("Kategooria");

        movieEntity.setName("Film");
        movieEntity.setCategory(categoryEntity);

        request.setDirector("Rezhisöör");
        request.setWriter("Autor");
        request.setStars("Näitlejad");
        request.setYear("2000");
        request.setCountry("Tootjamaa");
        request.setDescription("Kirjeldus");
        request.setMovieName(movieEntity.getName());

        details.setDirector("Rezhisöör");
        details.setWriter("Autor");
        details.setStars("Näitlejad");
        details.setYear("2000");
        details.setCountry("Tootjamaa");
        details.setDescription("Kirjeldus");
        details.setMovie(movieEntity);

        String price = "6";

        when(movieDetailsRepository.findByMovieName(movieEntity.getName())).thenReturn(details);
        when(priceService.setPrice(price)).thenReturn(price);
    }

    /**
     * Tests whether the details request returned by addedDetails method asserts not null and
     * whether hard coded movie properties and properties returned by addedDetails method are equal.
     */
    @Test
    void addDetails() {
        when(validationService.movieExists(request.getMovieName())).thenReturn(null);
        when(movieDetailsMapper.toEntity(request)).thenReturn(details);
        when(movieRepository.findByName(request.getMovieName())).thenReturn(movieEntity);
        when(movieDetailsRepository.save(Mockito.any(MovieDetails.class))).thenReturn(details);
        when(movieDetailsMapper.toDto(details)).thenReturn(request);

        MovieDetailsRequest addedDetails = movieDetailsService.addDetails(request);

        assertNotNull(addedDetails);
        assertEquals(request.getMovieName(), addedDetails.getMovieName());
        assertEquals(request.getDirector(), addedDetails.getDirector());
    }

    /**
     * Verifies whether updateDetails method calls the mapper layer update details method one time.
     */
    @Test
    void updateDetails() {
        when(movieDetailsMapper.partialUpdate(request, details)).thenReturn(details);
        movieDetailsService.updateDetails(request);

        verify(movieDetailsMapper, times(1)).partialUpdate(request, details);
    }

    /**
     * Verifies whether deleteDetails method calls repository delete details method one time.
     */
    @Test
    void deleteDetails() {
        movieDetailsService.deleteDetails(movieEntity.getName());

        verify(movieDetailsRepository, times(1)).delete(details);
    }
}