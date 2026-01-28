package com.ecommerce.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;


@Data
@Document(collection = "addresses")
public class Address {

    @Id
    private String id;

    private String userId;

    private String fullName;
    private String mobileNumber;

    private String addressLine;
    private String landmark;
    private String city;
    private String state;
    private String country;
    private String pincode;

    private boolean isDefault;

    private LocalDateTime createdAt = LocalDateTime.now();

    // getters & setters
}
