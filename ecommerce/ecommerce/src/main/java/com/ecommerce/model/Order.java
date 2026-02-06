//package com.ecommerce.model;
//
//import lombok.Data;
//import org.springframework.data.annotation.Id;
//import org.springframework.data.mongodb.core.mapping.Document;
//
//import java.time.LocalDateTime;
//@Data
//@Document(collection = "orders")
//public class Order {
//
//    @Id
//    private String id;
//
//    private String userId;
//    private String productId;
//    private int quantity;
//    private String currency;
//    private double amount;
//
//    private String status; // PENDING, PAID, FAILED
//    private String paymentOrderId;
//    private String addressId;
//    private LocalDateTime createdAt;
//}
//

package com.ecommerce.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Document(collection = "orders")
public class Order {

    @Id
    private String id;

    private String userId;
    private String addressId;

    private double totalAmount;      // ✅ new
    private String currency;

    private String status;           // PENDING, PAID, FAILED
    private String paymentOrderId;
    private LocalDateTime createdAt;
    private List<OrderItem> items;
}

