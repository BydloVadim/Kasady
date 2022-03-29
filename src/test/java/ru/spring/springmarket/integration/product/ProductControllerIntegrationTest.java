package ru.spring.springmarket.integration.product;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.spring.springmarket.dto.ProductDto;
import ru.spring.springmarket.exceptions.ResourceNotFoundException;
import ru.spring.springmarket.integration.IntegrationTest;

import ru.spring.springmarket.model.Product;
import ru.spring.springmarket.repositories.CategoryRepository;
import ru.spring.springmarket.repositories.ProductRepository;

import java.math.BigDecimal;

public class ProductControllerIntegrationTest extends IntegrationTest {

    private static final String BASE_URL = "/api/v1/products";

    @Autowired
    protected ProductRepository productRepository;

    @Autowired
    protected CategoryRepository categoryRepository;

    @AfterEach
    protected void tearDown() {
        productRepository.deleteAll();
    }

    @Test
    @DisplayName("Успешное сохранение")
    public void saveSuccess() throws Exception {
        ProductDto product = new ProductDto();
        product.setTitle("test");
        product.setCategoryTitle("Food");
        product.setPrice(BigDecimal.valueOf(100));
        MvcResult result = mvc.perform(MockMvcRequestBuilders.post(BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\":\"test\",\"categoryTitle\":\"Food\",\"price\":100}")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn();
        ProductDto actual = getResponse(result, ProductDto.class);
        Assertions.assertThat(actual.getTitle()).isEqualTo(product.getTitle());
        Assertions.assertThat(actual.getCategoryTitle()).isEqualTo(product.getCategoryTitle());
        Assertions.assertThat(actual.getPrice()).isEqualTo(product.getPrice());


    }


    @Test
    @DisplayName("Успешное удаление по идентификатору")
    public void DeleteByIdSuccess() throws Exception {
        Product product = new Product();
        product.setTitle("test");
        product.setCategory(categoryRepository.findCategoryByTitle("Food").orElseThrow(() -> new ResourceNotFoundException("Category not found")));
        product.setPrice(BigDecimal.valueOf(100));
        Product savedProduct = productRepository.save(product);
        mvc.perform(MockMvcRequestBuilders.delete(BASE_URL + "/" + savedProduct.getId()))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());


    }


}
