package ru.spring.springmarket.mappers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.spring.springmarket.beans.Cart;
import ru.spring.springmarket.dto.CartDto;


import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CartMapper {
    private final OrderItemMapper orderItemMapper;

    public CartDto toCartDto(Cart cart) {
        CartDto cartDto = new CartDto();
        cartDto.setTotalPrice(cart.getTotalPrice());
        cartDto.setItems(cart.getItems().stream().map(orderItemMapper::toOrderItemDto).collect(Collectors.toList()));

        return cartDto;

    }
}
