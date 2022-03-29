package ru.spring.springmarket.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.spring.springmarket.model.Category;
import ru.spring.springmarket.repositories.CategoryRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public Optional<Category> findByTitle (String title) {
        return categoryRepository.findCategoryByTitle(title);
    }


}
