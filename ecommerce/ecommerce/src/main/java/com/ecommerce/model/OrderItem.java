//package com.ecommerce.model;
//
//import lombok.Data;
//import org.springframework.data.annotation.Id;
//import org.springframework.data.mongodb.core.mapping.Document;
//
//@Data
//@Document(collection = "order_items")
//public class OrderItem {
//
//    @Id
//    private String id;
//
//    private String orderId;     // foreign key reference to Order
//    private String productId;
//    private int quantity;
//    private double amount;
//}

package com.ecommerce.model;

import lombok.Data;

@Data
public class OrderItem {

    private String productId;
    private int quantity;
    private double amount;
}
