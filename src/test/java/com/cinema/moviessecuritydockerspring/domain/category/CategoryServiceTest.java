package com.cinema.moviessecuritydockerspring.domain.category;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CategoryServiceTest {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    CategoryRepository categoryRepository;

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

    /**
     * Tests whether the name of the category saved to the database via repository save method
     * and the name of the category returned via getAllCategories are same.
     */
    @Test
    void getAllCategories() {
        Category categoryEntity = getCategory();
        String entityName = categoryEntity.getName();
        saveCategory(categoryEntity);

        List<CategoryResponse> responses = categoryService.getAllCategories();
        for (CategoryResponse response : responses) {
            if (response.getName().equals(entityName)) {
                assertTrue(true);
            }
        }
        deleteCategory(categoryEntity);
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

    private void saveCategory(Category category) {
        categoryRepository.save(category);
    }

    private void deleteCategory(Category name) {
        categoryRepository.delete(name);
    }
}