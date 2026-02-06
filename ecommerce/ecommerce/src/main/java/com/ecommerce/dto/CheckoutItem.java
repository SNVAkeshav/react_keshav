package com.ecommerce.dto;

import lombok.Data;

@Data
public class CheckoutItem {
    private String productId;
    private int quantity;
}
