package ru.spring.springmarket.services;

import ru.spring.springmarket.model.Category;

import java.util.Optional;

public interface CategoryService {
    public Optional<Category> findByTitle (String title);
}
