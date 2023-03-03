package com.cinema.moviessecuritydockerspring.domain.category;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    /**
     * Tests equality between the name of the category saved database via repository save method and
     * the name of the category returned via findByName method.
     */
    @Test
    void findByName() {
        Category category = new Category();
        category.setName("Kategooria");
        categoryRepository.save(category);

        Category byName = categoryRepository.findByName(category.getName());
        String name = byName.getName();

        assertEquals("Kategooria", name);

        categoryRepository.delete(byName);
    }
}