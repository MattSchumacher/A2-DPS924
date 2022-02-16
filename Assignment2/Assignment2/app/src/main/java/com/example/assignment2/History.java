package com.example.assignment2;

import java.util.Date;

public class History extends Product {
    Date purchaseDate;

    public History(Integer id, Product product, Integer quantity) {
        super(id, product.name, product.price, quantity);
        purchaseDate = new Date();
    }
}
