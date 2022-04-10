package ru.spring.springmarket.controllers;

import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.spring.springmarket.beans.Cart;
import ru.spring.springmarket.dto.CartDto;
import ru.spring.springmarket.exceptions.ResourceNotFoundException;
import ru.spring.springmarket.mappers.CartMapper;
import ru.spring.springmarket.model.Product;
import ru.spring.springmarket.services.ProductService;



@Api(value = "Set of endpoints for cart", produces = "application/json", consumes = "application/json", protocols = "http")
@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {
    private final Cart cart;
    private final ProductService productService;
    private final CartMapper cartMapper;


    @ApiOperation("Returns cart")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Cart found"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Cart not found"),
            @ApiResponse(code = 500, message = "Server error")
    })
    @GetMapping
    public CartDto getCart() {
        return cartMapper.toCartDto(cart);
    }


    @ApiOperation("Adds product to cart")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 201, message = "Product added to cart"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Product not found"),
            @ApiResponse(code = 500, message = "Server error")
    })
    @PostMapping("/add/{id}")
    public void addToCart(
            @ApiParam(
                    name = "id",
                    type = "Long",
                    value = "Product ID"
            )
            @PathVariable Long id) {

        if (cart.addToCart(id)) {
            return;
        }
        Product p = productService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Unable add product to cart. Product not found id: " + id));
        cart.addToCart(p);
    }


    @ApiOperation("Clears cart")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Cart cleared"),
            @ApiResponse(code = 204, message = "Cart is empty"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),

            @ApiResponse(code = 500, message = "Server error")
    })
    @DeleteMapping("/clear")
    public void clearCart() {
        cart.clear();
    }


    @ApiOperation("Deletes all products with the given ID from cart")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Product with given ID deleted from cart"),
            @ApiResponse(code = 204, message = "Product with given ID not found"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 500, message = "Server error")
    })
    @DeleteMapping("/delete/{id}")
    public void deleteFromCart(
            @ApiParam(
                    name = "id",
                    type = "Long",
                    value = "Product ID"
            )
            @PathVariable Long id) {

        cart.deleteFromCart(id);
    }
}
