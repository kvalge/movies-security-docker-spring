package com.cinema.moviessecuritydockerspring.domain.category;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Resource
    private CategoryService categoryService;

    @PostMapping("/new")
    public void addNewCategory(@RequestParam String name) {
        categoryService.addNewCategory(name);
    }

    @GetMapping
    public List<CategoryResponse> getAllCategories() {
        return categoryService.getAllCategories();
    }
}
