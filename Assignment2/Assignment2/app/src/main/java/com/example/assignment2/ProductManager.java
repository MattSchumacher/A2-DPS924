package com.example.assignment2;

import java.util.ArrayList;
import java.util.List;

public class ProductManager {

    private List<Product> allProducts = new ArrayList<Product>() {
        {
            add(new Product(1, "Pants", 20.44, 10));
            add(new Product(2, "Shoes", 10.44, 100));
            add(new Product(3, "Hats", 5.9, 30));
        }
    };

    public List<Product> getAllProducts() {
        return allProducts;
    }
}
