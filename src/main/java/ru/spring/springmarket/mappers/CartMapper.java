package ru.spring.springmarket.mappers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.spring.springmarket.beans.Cart;
import ru.spring.springmarket.dto.CartDto;
import ru.spring.springmarket.dto.OrderItemDto;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CartMapper {
    private final OrderItemMapper orderItemMapper;

    public CartDto fromCartToCartDto(Cart cart) {
        CartDto cartDto = new CartDto();
        cartDto.setTotalPrice(cart.getTotalPrice());
        cartDto.setItems(cart.getItems().stream().map(orderItemMapper::fromOrderItemToOrderItemDto).collect(Collectors.toList()));

        return cartDto;

    }
}
