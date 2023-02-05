package com.cinema.moviessecuritydockerspring.domain.category;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Resource
    private CategoryMapper categoryMapper;

    @Resource
    private CategoryRepository categoryRepository;

    public void addNewCategory(String name) {
        Category category = categoryMapper.toEntity(name);
        categoryRepository.save(category);
    }

    public List<CategoryResponse> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categoryMapper.toResponse(categories);
    }

    public void deleteByName(String name) {
        Category category = categoryRepository.findByName(name);

        categoryRepository.delete(category);
    }

    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }
}
