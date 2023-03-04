package com.cinema.moviessecuritydockerspring.domain.category;

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
    private ValidationService validationService;

    public void addNewCategory(String name) {
        validationService.categoryExists(name);

        Category category = categoryMapper.toEntity(name);
        categoryRepository.save(category);
    }

    public List<CategoryResponse> getAllCategories() {
        validationService.categoriesNotFound();

        List<Category> categories = categoryRepository.findAll();
        return categoryMapper.toResponse(categories);
    }

    public void deleteByName(String name) {
        validationService.categoryNotFound(name);

        Category category = categoryRepository.findByName(name);

        categoryRepository.delete(category);
    }

    public void deleteById(Long id) {
        validationService.categoryNotFound(id);

        categoryRepository.deleteById(id);
    }
}
