package ru.spring.springmarket.controllers;

import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import ru.spring.springmarket.dto.ProductDto;
import ru.spring.springmarket.exceptions.ResourceNotFoundException;
import ru.spring.springmarket.mappers.ProductMapper;
import ru.spring.springmarket.model.Product;
import ru.spring.springmarket.repositories.specifications.ProductSpecifications;
import ru.spring.springmarket.services.ProductService;

import java.math.BigDecimal;

@Api(value = "Set of endpoints for products", produces = "application/json", consumes = "application/json", protocols = "http")
@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final ProductMapper productMapper;


    @ApiOperation("Returns products")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Products found"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Products not found"),
            @ApiResponse(code = 500, message = "Server error")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "title", example = "Milk", type = "String", dataTypeClass = String.class, required = false, paramType = "query"),
            @ApiImplicitParam(name = "min_price", example = "100.00", type = "BigDecimal", dataTypeClass = BigDecimal.class, required = false, paramType = "query"),
            @ApiImplicitParam(name = "max_price", example = "2000.00", type = "BigDecimal", dataTypeClass = BigDecimal.class, required = false, paramType = "query")
    })
    @GetMapping
    public Page<ProductDto> findAllProducts(

            @RequestParam MultiValueMap<String, String> params,
            @RequestParam(name = "p", defaultValue = "1") Integer page
    ) {
        if (page < 1) {
            page = 1;
        }

        return productService.findAll(ProductSpecifications.build(params), page, 4).map(productMapper::toProductDto);
    }


    @ApiOperation("Returns product with the given ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Product with the given ID found"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Product with given ID not found"),
            @ApiResponse(code = 500, message = "Server error")
    })
    @GetMapping("/{id}")
    public ProductDto findProductById(
            @ApiParam(
                    name = "id",
                    type = "Long",
                    value = "Product ID"
            )
            @PathVariable Long id) {
        Product p = productService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product with id: " + id + " doesn't exist"));

        return productMapper.toProductDto(p);
    }

    @ApiOperation("Saves new product")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Product saved"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Product not found"),
            @ApiResponse(code = 500, message = "Server error")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDto saveNewProduct(@RequestBody ProductDto productDto) {
        productDto.setId(null);
        Product p = productService.save(productMapper.toProduct(productDto));
        return productMapper.toProductDto(p);
    }

    @ApiOperation("Updates product")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 201, message = "Product updated"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Product not found"),
            @ApiResponse(code = 500, message = "Server error")
    })
    @PutMapping
    public ProductDto updateProduct(@RequestBody ProductDto productDto) {

        Product p = productService.save(productMapper.toProduct(productDto));
        return productMapper.toProductDto(p);
    }

    @ApiOperation("Deletes product with the given ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Product with given ID deleted"),
            @ApiResponse(code = 204, message = "Product with given ID not found"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 500, message = "Server error")
    })
    @DeleteMapping("/{id}")
    public void deleteProduct(
            @ApiParam(
                    name = "id",
                    type = "Long",
                    value = "Product ID"
            )
            @PathVariable Long id) {
        productService.deleteById(id);
    }
}
