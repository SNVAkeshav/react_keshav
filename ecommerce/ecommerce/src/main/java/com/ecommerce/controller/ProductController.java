package com.ecommerce.controller;

import com.ecommerce.model.Product;
import com.ecommerce.service.ProductService;
import com.ecommerce.utils.AESUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin("*")
public class ProductController {

    @Autowired
    private ProductService productService;

    // ADD PRODUCT API
    @PostMapping("/add")
    public Product addProduct(
            @RequestParam String name,
            @RequestParam(required = false) String color,
            @RequestParam String currency,
            @RequestParam double price,
            @RequestParam String description,
            @RequestParam List<MultipartFile> images
    ) {
        return productService.addProduct(
                name, color, currency ,price, description, images
        );
    }

    // HOME PAGE VIEW (encrypted response)
    @GetMapping("/home")
    public String homeProductsEncrypted() {
        List<Product> products = productService.getAllProducts();
        try {
            // Convert list to JSON string
            String json = new ObjectMapper().writeValueAsString(products);
            // Encrypt JSON string using AESUtil
            return AESUtil.encrypt(json);
        } catch (Exception e) {
            throw new RuntimeException("Encryption error: " + e.getMessage());
        }
    }

    // PRODUCT DETAIL PAGE (encrypted response)
    @GetMapping("/{id}")
    public String productDetailEncrypted(@PathVariable String id) {
        return productService.getEncryptedProductById(id);
    }
}
