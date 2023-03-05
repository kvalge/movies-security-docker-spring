package com.cinema.moviessecuritydockerspring.domain.movie;

import com.cinema.moviessecuritydockerspring.domain.category.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class MovieRepositoryTest {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @BeforeEach
    void setup() {
        Category categoryEntity = new Category();
        categoryEntity.setName("Kategooria");

        Movie movieEntity = new Movie();
        movieEntity.setName("Film");
        movieEntity.setCategory(categoryEntity);

        testEntityManager.persist(categoryEntity);
        testEntityManager.persist(movieEntity);
    }

    @Test
    void findByName() {
        Movie movie = movieRepository.findByName("Film");
        assertEquals("Film", movie.getName());
    }
}