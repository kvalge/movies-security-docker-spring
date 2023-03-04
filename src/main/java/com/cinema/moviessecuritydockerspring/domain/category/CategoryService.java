package com.cinema.moviessecuritydockerspring.domain.category;

import com.cinema.moviessecuritydockerspring.domain.movie.MovieRepository;
import com.cinema.moviessecuritydockerspring.validation.ValidationService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Resource
    private CategoryMapper categoryMapper;

    @Resource
    private CategoryRepository categoryRepository;

    @Resource
    private MovieRepository movieRepository;

    @Resource
    private ValidationService validationService;

    /**
     * Checks isn't the category already in the database before adding it as new to the database.
     */
    public void addNewCategory(String name) {
        validationService.categoryExists(name);

        Category category = categoryMapper.toEntity(name);
        categoryRepository.save(category);
    }

    /**
     * Checks isn't the category database empty before returning all categories.
     */
    public List<CategoryResponse> getAllCategories() {
        validationService.categoriesNotFound();

        List<Category> categories = categoryRepository.findAll();
        return categoryMapper.toResponse(categories);
    }

    /**
     * Checks is there a requested category in the database and whether it isn't in use
     * before finding the category by name for deletion.
     */
    public void deleteByName(String name) {
        validationService.categoryNotFound(name);
        validationService.categoryIsInUse(name);

        Category category = categoryRepository.findByName(name);

        categoryRepository.delete(category);
    }


    /**
     * Checks is there a requested category in the database and whether it isn't in use
     * before finding the category by id for deletion.
     */
    public void deleteById(Long id) {
        validationService.categoryNotFound(id);
        validationService.categoryIsInUse(id);

        categoryRepository.deleteById(id);
    }
}
