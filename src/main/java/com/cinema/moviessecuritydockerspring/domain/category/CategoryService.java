package com.cinema.moviessecuritydockerspring.domain.category;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

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
}
