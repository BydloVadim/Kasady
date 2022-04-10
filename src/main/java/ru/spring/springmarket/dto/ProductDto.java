package ru.spring.springmarket.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;



import java.math.BigDecimal;


@ApiModel(description = "Data transfer object for product")
@Data
@NoArgsConstructor
public class ProductDto {
    @ApiModelProperty(notes = "Unique identifier of item's product. No two products can have same id", example = "1", required = false, position = 0)
    private Long id;
    @ApiModelProperty(notes = "Name of product", example = "Bread", required = true, position = 1)
    private String title;
    @ApiModelProperty(notes = "Name of product's category", example = "Food", required = true, position = 2)
    private String categoryTitle;
    @ApiModelProperty(notes = "Price of product (rub.)", example = "27.00", required = true, position = 3)
    private BigDecimal price;



}
