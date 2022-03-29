package ru.spring.springmarket.controllers;

import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.spring.springmarket.dto.OrderDto;
import ru.spring.springmarket.exceptions.ResourceNotFoundException;
import ru.spring.springmarket.mappers.OrderMapper;
import ru.spring.springmarket.model.User;
import ru.spring.springmarket.services.OrderService;
import ru.spring.springmarket.services.UserService;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Api(value = "Set of endpoints for orders", produces = "application/json", consumes = "application/json", protocols = "http")
@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final UserService userService;
    private final OrderMapper orderMapper;

    @ApiOperation("Creates order")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 201, message = "Order created"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "User not found"),
            @ApiResponse(code = 500, message = "Server error")
    })
    @PostMapping
    public void createOrder(
            Principal principal) {
        User user = userService.findByUsername(principal.getName()).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        orderService.createOrder(user);
    }

    @ApiOperation("Returns orders")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Orders found"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Orders not found"),
            @ApiResponse(code = 500, message = "Server error")
    })
    @GetMapping
    public List<OrderDto> getOrders(
            Principal principal) {
        User user = userService.findByUsername(principal.getName()).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return orderService.findAllByUser(user).stream().map(orderMapper::fromOrderToOrderDto).collect(Collectors.toList());
    }

}
