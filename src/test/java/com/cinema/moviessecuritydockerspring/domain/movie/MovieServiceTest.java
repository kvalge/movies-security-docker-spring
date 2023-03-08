package com.cinema.moviessecuritydockerspring.domain.movie;

import com.cinema.moviessecuritydockerspring.domain.category.Category;
import com.cinema.moviessecuritydockerspring.domain.category.CategoryRepository;
import com.cinema.moviessecuritydockerspring.domain.moviedetails.MovieDetailsService;
import com.cinema.moviessecuritydockerspring.domain.rental.Rental;
import com.cinema.moviessecuritydockerspring.domain.rental.RentalRepository;
import com.cinema.moviessecuritydockerspring.domain.role.Role;
import com.cinema.moviessecuritydockerspring.domain.user.User;
import com.cinema.moviessecuritydockerspring.validation.ValidationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

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
    private MovieDetailsService movieDetailsService;

    @Mock
    private RentalRepository rentalRepository;

    @Mock
    private ValidationService validationService;

    Movie movie = new Movie();
    Movie newMovie = new Movie();
    MovieRequest request = new MovieRequest();
    MovieRequest newRequest = new MovieRequest();
    MovieResponse response = new MovieResponse();
    Category category = new Category();

    @BeforeEach
    void setup() {
        category.setName("Kategooria");

        movie.setName("Film");
        movie.setCategory(category);

        newMovie.setName("Muuvi");
        newMovie.setCategory(category);

        request.setName("Film");
        request.setCategoryName(category.getName());

        newRequest.setName("Muuvi");
        newRequest.setCategoryName(category.getName());

        response.setMovieName("Film");
        response.setCategoryName(category.getName());
        response.setAvRating(5F);

        Role role = new Role();
        role.setName("ROLE_Roll");

        Set<Role> roles = new HashSet<>();
        roles.add(role);

        User user = new User();
        user.setName("Nimi");
        user.setUsername("Kasutaja");
        user.setEmail("Email");
        user.setPassword("Salasõna");
        user.setRoles(roles);

        Rental rental = new Rental();
        rental.setUser(user);
        rental.setMovie(movie);
        rental.setRentalDate(LocalDate.now());
        rental.setRating(5);

        List<Rental> rentalList = new ArrayList<>();
        rentalList.add(rental);

        when(movieRepository.findByName("Film")).thenReturn(movie);
        when(rentalRepository.findByMovieName(movie.getName())).thenReturn(rentalList);
        when(movieMapper.toRequest(movie)).thenReturn(request);
        when(validationService.movieNotFound("Film")).thenReturn("Film");
    }

    /**
     * Tests whether addNewMovie will not return null and equality between the hard coded movie name and
     * the movie name returned by addNewMovie method.
     */
    @Test
    void addNewMovie() {
        Movie mapped = new Movie();
        mapped.setName(newRequest.getName());
        mapped.setCategory(category);

        when(movieMapper.toEntity(newRequest)).thenReturn(mapped);
        when(movieMapper.toRequest(newMovie)).thenReturn(newRequest);
        when(movieRepository.save(Mockito.any(Movie.class))).thenReturn(newMovie);
        when(categoryRepository.findByName(newRequest.getCategoryName())).thenReturn(category);

        MovieRequest addedNewMovie = movieService.addNewMovie(newRequest);

        assertNotNull(addedNewMovie);
        assertEquals(newMovie.getName(), addedNewMovie.getName());
    }

    /**
     * Tests equality between the name and the category name of the hard coded movie
     * entity and the movie properties returned via getByName method.
     */
    @Test
    void getByName() {
        when(movieMapper.toResponse(movie)).thenReturn(response);

        String name = movieService.getByName(movie.getName()).getMovieName();
        String categoryName = movieService.getByName(movie.getName()).getCategoryName();

        String movieName = movie.getName();
        String movieCategory = movie.getCategory().getName();

        assertEquals(movieName, name);
        assertEquals(movieCategory, categoryName);
    }

    /**
     * Tests whether getAll method asserts the returned list not to be null, not to be empty and
     * to have same size as a hard coded list. And whether list elements has the same movie name as
     * hard coded list elements.
     */
    @Test
    void getAll() {
        List<Movie> movies = new ArrayList<>();
        movies.add(movie);
        movies.add(newMovie);

        List<MovieRequest> requests = new ArrayList<>();
        requests.add(request);
        requests.add(newRequest);

        when(movieRepository.findAll()).thenReturn(movies);
        when(movieMapper.toRequest(movies)).thenReturn(requests);

        List<MovieRequest> movieRequests = movieService.getAll();

        assertThat(movieRequests).isNotNull().isNotEmpty().hasSize(2);
        assertThat(movieRequests.get(0).getName()).isEqualTo(movie.getName());
        assertThat(movieRequests.get(1).getName()).isEqualTo(newMovie.getName());
    }

    /**
     * Verifies whether updateMovie method calls the mapper layer update movie method one time.
     */
    @Test
    void updateMovie() {
        when(categoryRepository.findByName(request.getCategoryName())).thenReturn(category);
        when(movieMapper.update(request, movie)).thenReturn(newMovie);

        movieService.updateMovie(request);

        verify(movieMapper, times(1)).update(request, movie);
    }

    /**
     * Verifies whether deleteByName method calls repository delete movie method one time.
     */
    @Test
    void deleteByName() {
        doNothing().when(movieDetailsService).deleteDetails(movie.getName());

        movieService.deleteByName(movie.getName());

        verify(movieRepository, times(1)).delete(movie);
    }
}