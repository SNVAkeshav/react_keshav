package com.ecommerce.model;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "products")
public class Product {

    @Id
    private String id;

    private String name;        // required
    private String color;       // optional
    private String fragnance;
    private String currency;
    private double price;       // required
    private String description; // required
    private List<String> images; // Cloudinary URLs
}
