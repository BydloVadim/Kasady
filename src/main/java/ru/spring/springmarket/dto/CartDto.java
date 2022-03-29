package ru.spring.springmarket.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.spring.springmarket.beans.Cart;


import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@ApiModel(description = "Data transfer object for cart session bean")
@NoArgsConstructor
@Data
public class CartDto {

    @ApiModelProperty(notes = "List of data transfer objects of items, representing product in cart and it's quantity", required = true, position = 0)
    private List<OrderItemDto> items;
    @ApiModelProperty(notes = "Total price of all products in cart", example = "3000.00", required = true, position = 1)
    private BigDecimal totalPrice;

    public List<OrderItemDto> getItems() {
        return Collections.unmodifiableList(items);
    }





}
