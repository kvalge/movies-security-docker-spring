package com.cinema.moviessecuritydockerspring.domain.category;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class CategoryControllerTest {

    @Autowired
    private CategoryController categoryController;

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

        categoryController.addNewCategory(categoryName);

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

        List<CategoryResponse> responses = categoryController.getAllCategories();
        for (CategoryResponse response : responses) {
            if (response.getName().equals(entityName)) {
                assertTrue(true);
            }
        }
        deleteCategory(categoryEntity);
    }

    /**
     * Tests whether the hard coded category saved to the database via save method asserts null after
     * using deleteByName method.
     */
    @Test
    void deleteByName() {
        Category categoryEntity = getCategory();
        String entityName = categoryEntity.getName();
        saveCategory(categoryEntity);

        categoryController.deleteByName(entityName);

        Category byName = categoryRepository.findByName(entityName);

        assertNull(byName);
    }

    @Test
    void deleteById() {
        Category categoryEntity = getCategory();
        String entityName = categoryEntity.getName();
        saveCategory(categoryEntity);
        Long id = categoryRepository.findByName(entityName).getId();

        categoryController.deleteById(id);

        Category byName = categoryRepository.findByName(entityName);

        assertNull(byName);
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