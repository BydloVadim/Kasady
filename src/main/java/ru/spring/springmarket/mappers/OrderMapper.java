package ru.spring.springmarket.mappers;

import org.springframework.stereotype.Component;
import ru.spring.springmarket.dto.OrderDto;
import ru.spring.springmarket.model.Order;

@Component
public class OrderMapper {

    public OrderDto toOrderDto(Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(order.getId());
        orderDto.setUsername(order.getUser().getUsername());
        orderDto.setPrice(order.getPrice());



        String date1 = order.getUpdatedAt().toString().replace('T', ' ');
        String date2 = date1.substring(0, date1.indexOf('.'));
        orderDto.setDate(date2);

        return orderDto;

    }
}
