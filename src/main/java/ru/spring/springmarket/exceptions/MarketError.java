package ru.spring.springmarket.exceptions;

import lombok.Data;

import java.util.Date;

@Data
public class MarketError {

    private String message;
    private Date timestamp;

    public MarketError(String message) {

        this.message = message;
        this.timestamp = new Date();
    }
}
