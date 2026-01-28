package com.ecommerce.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
@Data
@Document(collection = "orders")
public class Order {

    @Id
    private String id;

    private String userId;
    private String productId;
    private int quantity;
    private String currency;
    private double amount;

    private String status; // PENDING, PAID, FAILED
    private String paymentOrderId;
    private String addressId;
    private LocalDateTime createdAt;
}

