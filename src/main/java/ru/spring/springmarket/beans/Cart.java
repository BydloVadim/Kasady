package ru.spring.springmarket.beans;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import ru.spring.springmarket.model.OrderItem;
import ru.spring.springmarket.model.Product;


import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
@Data
public class Cart {


    private List<OrderItem> items;
    private BigDecimal totalPrice;


    public List<OrderItem> getItems() {
        return Collections.unmodifiableList(items);
    }


    @PostConstruct
    public void init() {
        this.items = new ArrayList<>();
        this.totalPrice = BigDecimal.ZERO;
    }

    public void clear() {
        items.clear();
        totalPrice = BigDecimal.ZERO;
    }


    public boolean addToCart(Long id) {
        for (OrderItem o : items) {
            if (o.getProduct().getId().equals(id)) {
                o.incrementQuantity();
                recalculate();
                return true;
            }
        }
        return false;
    }


    public void addToCart(Product product) {

        items.add(new OrderItem(product));
        recalculate();
    }

    public void deleteFromCart(Long productId) {
        System.out.println("1");
        for (OrderItem o : items) {
            if (o.getProduct().getId().equals(productId)) {
                items.remove(o);
                recalculate();
                return;

            }
        }


    }


    public void recalculate() {
        totalPrice = BigDecimal.ZERO;
        for (OrderItem o : items) {
            totalPrice = totalPrice.add(o.getPrice());

        }
    }
}
