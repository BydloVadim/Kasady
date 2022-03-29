package ru.spring.springmarket.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.spring.springmarket.model.OrderItem;


import java.math.BigDecimal;

@ApiModel(description = "Data transfer object for item, representing product and it's quantity")
@NoArgsConstructor
@Data
public class OrderItemDto {
    @ApiModelProperty(notes = "Unique identifier of item's product. No two products can have same id", example = "1", required = true, position = 0)
    private Long productId;
    @ApiModelProperty(notes = "Name of item's product", example = "Milk", required = true, position = 1)
    private String productTitle;
    @ApiModelProperty(notes = "Amount of current item's product", example = "7", required = true, position = 2)
    private Integer quantity;
    @ApiModelProperty(notes = "Price of unit of product (rub.)", example = "15.00", required = true, position = 3)
    private BigDecimal pricePerProduct;
    @ApiModelProperty(notes = "Total price of all products (rub.)", example = "226.00", required = true, position = 4)
    private BigDecimal price;



}
