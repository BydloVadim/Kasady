package ru.spring.springmarket.mappers;

import org.springframework.stereotype.Component;
import ru.spring.springmarket.dto.OrderItemDto;
import ru.spring.springmarket.model.OrderItem;

@Component
public class OrderItemMapper {

    public OrderItemDto toOrderItemDto(OrderItem orderItem) {
        OrderItemDto orderItemDto = new OrderItemDto();
        orderItemDto.setProductId(orderItem.getProduct().getId());
        orderItemDto.setProductTitle(orderItem.getProduct().getTitle());
        orderItemDto.setQuantity(orderItem.getQuantity());
        orderItemDto.setPricePerProduct(orderItem.getPricePerProduct());
        orderItemDto.setPrice(orderItem.getPrice());

        return orderItemDto;

    }
}
