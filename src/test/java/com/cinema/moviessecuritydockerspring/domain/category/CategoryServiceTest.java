package com.cinema.moviessecuritydockerspring.domain.category;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CategoryServiceTest {

    @Autowired
    private CategoryService categoryService;

    @Autowired CategoryRepository categoryRepository;

    @Test
    void addNewCategory() {
    }

    @Test
    void getAllCategories() {
    }

    @Test
    void deleteByName() {
    }

    @Test
    void deleteById() {
    }
}