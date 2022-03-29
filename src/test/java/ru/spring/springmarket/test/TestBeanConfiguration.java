package ru.spring.springmarket.test;


import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import ru.spring.springmarket.services.ProductService;

@TestConfiguration
public class TestBeanConfiguration {


    @Bean
    @Primary
    public ProductService testProductService() {
        return new TestProductService();
    }


}
