package ru.spring.springmarket.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import ru.spring.springmarket.model.Product;
import ru.spring.springmarket.repositories.ProductRepository;

import java.util.Optional;

public interface ProductService {


    public Optional<Product> findById(Long id);


    public Page<Product> findAll(Specification<Product> spec, int page, int pageSize);

    public Product save(Product product);

    public void deleteById(Long id);

}
