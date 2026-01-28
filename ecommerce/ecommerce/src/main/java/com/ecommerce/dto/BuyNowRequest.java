package com.ecommerce.dto;

import lombok.Data;

@Data
public class BuyNowRequest {

    private String productId;
    private int quantity;
    private String userId;
    private String currency;
    private double amount;
    private String addressId;
}
