package com.ecommerce.model;

import lombok.Data;

@Data
public class CartItem {

    private String productId;
    private String productName;
    private String currency;
    private double price;
    private int quantity;
    private String image;
}
