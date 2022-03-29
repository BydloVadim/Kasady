package ru.spring.springmarket.test;


import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import ru.spring.springmarket.model.Product;
import ru.spring.springmarket.services.ProductService;


import java.util.Optional;


public class TestProductService implements ProductService {
    @Override
    public Optional<Product> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Page<Product> findAll(Specification<Product> spec, int page, int pageSize) {
        return null;
    }

    @Override
    public Product save(Product product) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

}
