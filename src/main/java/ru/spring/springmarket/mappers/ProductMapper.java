package ru.spring.springmarket.mappers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.spring.springmarket.dto.ProductDto;
import ru.spring.springmarket.exceptions.ResourceNotFoundException;
import ru.spring.springmarket.model.Product;
import ru.spring.springmarket.services.CategoryService;

@Component
@RequiredArgsConstructor
public class ProductMapper {
    private final CategoryService categoryService;


    public ProductDto fromProductToProductDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setTitle(product.getTitle());
        productDto.setCategoryTitle(product.getCategory().getTitle());
        productDto.setPrice(product.getPrice());
        return productDto;

    }

    public Product fromProductDtoToProduct(ProductDto productDto) {
        Product product = new Product();
        product.setId(productDto.getId());
        product.setTitle(productDto.getTitle());
        product.setCategory(categoryService.findByTitle(productDto.getCategoryTitle()).orElseThrow(() -> new ResourceNotFoundException("Category not found")));
        product.setPrice(productDto.getPrice());
        return product;

    }


}
