package com.exercise.dealersolution.repository;

import lombok.AllArgsConstructor;

import lombok.Getter;
import lombok.Setter;



@AllArgsConstructor
@Setter
@Getter
public class Product {

    private Integer productModelId;
    private String description;
    private Integer status;
    private Double price;
    private Integer quantity;
    private String deadlineProduct;
}
