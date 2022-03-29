package ru.spring.springmarket.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "Username and password, sent by client for authorization")
@Data
public class JwtRequest {
    @ApiModelProperty(notes = "Username for authorization", example = "Bob100", required = true, position = 0)
    private String username;
    @ApiModelProperty(notes = "Password for authorization", example = "12sdfsdfsdf", required = true, position = 1)
    private String password;
}
