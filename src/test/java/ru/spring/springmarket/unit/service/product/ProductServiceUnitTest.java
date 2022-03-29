package ru.spring.springmarket.unit.service.product;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import org.springframework.data.jpa.domain.Specification;

import ru.spring.springmarket.model.Product;
import ru.spring.springmarket.repositories.ProductRepository;
import ru.spring.springmarket.services.ProductService;
import ru.spring.springmarket.services.ProductServiceImpl;
import ru.spring.springmarket.unit.service.ServiceUnitTest;


@SpringBootTest(classes = ProductServiceImpl.class)
public class ProductServiceUnitTest extends ServiceUnitTest {

    @Autowired
    private ProductService productService;

    @MockBean
    private ProductRepository productRepository;

    @Test
    public void findAllSuccess() {
        Specification<Product> spec = Specification.where(null);
        Mockito.doReturn(Page.empty()).when(productRepository).findAll(spec, PageRequest.of(1, 4));
        Page<Product> actual = productService.findAll(spec, 1, 4);
        Assertions.assertThat(actual).isNullOrEmpty();


    }


}
