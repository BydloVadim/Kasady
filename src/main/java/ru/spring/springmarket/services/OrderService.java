package ru.spring.springmarket.services;

import ru.spring.springmarket.exceptions.ResourceNotFoundException;
import ru.spring.springmarket.model.Order;
import ru.spring.springmarket.model.OrderItem;
import ru.spring.springmarket.model.Product;
import ru.spring.springmarket.model.User;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public interface OrderService {
    public void createOrder(User user);

    public List<Order> findAllByUser(User user);
}
