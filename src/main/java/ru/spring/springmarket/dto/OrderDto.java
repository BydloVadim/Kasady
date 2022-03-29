package ru.spring.springmarket.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.spring.springmarket.model.Order;


import java.math.BigDecimal;

@ApiModel(description = "Data transfer object for order of client")
@Data
@NoArgsConstructor
public class OrderDto {
    @ApiModelProperty(notes = "Unique identifier of order. No two orders can have same id", example = "1", required = false, position = 0)
    private Long id;
    @ApiModelProperty(notes = "Total price of order (rub.)", example = "22000.00", required = true, position = 1)
    private BigDecimal price;
    @ApiModelProperty(notes = "Username of client, who made order", example = "Bob100", required = true, position = 2)
    private String username;
    @ApiModelProperty(notes = "Creation or last update timestamp of order", example = "1999-03-15 21:55:19", required = true, position = 3)
    private String date;


}
