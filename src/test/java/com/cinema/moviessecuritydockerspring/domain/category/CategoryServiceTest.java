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

    /**
     * Tests equality between the name of the category saved to the database via addNewCategory method
     * and the name of the category returned via repository findByName method.
     */
    @Test
    void addNewCategory() {
        Category category = getCategory();
        String categoryName = category.getName();

        categoryService.addNewCategory(categoryName);

        Category byName = categoryRepository.findByName(categoryName);
        String name = byName.getName();

        assertEquals(categoryName, name);

        deleteCategory(byName);
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

    /**
     * Hard coded category entity.
     */
    private static Category getCategory() {
        Category category = new Category();
        category.setName("Kategooria");
        return category;
    }

    private void deleteCategory(Category name) {
        categoryRepository.delete(name);
    }
}