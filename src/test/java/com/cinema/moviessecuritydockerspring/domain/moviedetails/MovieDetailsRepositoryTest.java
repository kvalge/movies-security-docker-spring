package com.cinema.moviessecuritydockerspring.domain.moviedetails;

import com.cinema.moviessecuritydockerspring.domain.category.Category;
import com.cinema.moviessecuritydockerspring.domain.movie.Movie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class MovieDetailsRepositoryTest {

    @Autowired
    private MovieDetailsRepository movieDetailsRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    Movie movieEntity = new Movie();
    MovieDetails details = new MovieDetails();

    @BeforeEach
    void setup() {
        Category categoryEntity = new Category();
        categoryEntity.setName("Kategooria");

        movieEntity.setName("Film");
        movieEntity.setCategory(categoryEntity);

        details.setDirector("Rezhisöör");
        details.setWriter("Autor");
        details.setStars("Näitlejad");
        details.setYear("2000");
        details.setCountry("Tootjamaa");
        details.setDescription("Kirjeldus");
        details.setMovie(movieEntity);

        testEntityManager.persist(categoryEntity);
        testEntityManager.persist(movieEntity);
        testEntityManager.persist(details);
    }
    @Test
    void findByMovieName() {
        MovieDetails movieDetails = movieDetailsRepository.findByMovieName(movieEntity.getName());

        assertEquals(movieEntity.getName(), movieDetails.getMovie().getName());
        assertEquals(details.getDirector(), movieDetails.getDirector());
    }
}