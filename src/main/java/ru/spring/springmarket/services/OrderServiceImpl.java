package ru.spring.springmarket.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import ru.spring.springmarket.beans.Cart;

import ru.spring.springmarket.exceptions.ResourceNotFoundException;
import ru.spring.springmarket.model.Order;
import ru.spring.springmarket.model.OrderItem;
import ru.spring.springmarket.model.Product;
import ru.spring.springmarket.model.User;
import ru.spring.springmarket.repositories.OrderRepository;


import java.math.BigDecimal;
import java.util.ArrayList;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final ProductService productService;

    private final Cart cart;


    @Transactional
    public void createOrder(User user) {
        Order order = new Order();
        order.setPrice(cart.getTotalPrice());
        order.setUser(user);
        order.setItems(new ArrayList<>());
        for (OrderItem o : cart.getItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setQuantity(o.getQuantity());
            Product product = productService.findById(o.getProduct().getId()).orElseThrow(() -> new ResourceNotFoundException("Product not found"));
            orderItem.setPrice(product.getPrice().multiply(BigDecimal.valueOf(o.getQuantity())));
            orderItem.setPricePerProduct(product.getPrice());
            orderItem.setProduct(product);
            order.getItems().add(orderItem);
        }


        orderRepository.save(order);
        cart.clear();

    }

    public List<Order> findAllByUser(User user) {
        return orderRepository.findAllByUser(user);
    }
}
