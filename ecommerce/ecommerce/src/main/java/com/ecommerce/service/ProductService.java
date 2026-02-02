package com.ecommerce.service;

import com.ecommerce.model.Product;
import com.ecommerce.repository.ProductRepository;
import com.ecommerce.utils.AESUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CloudinaryService cloudinaryService;

    // ================= ADD PRODUCT =================
    public Product addProduct(
            String name,
            String color,
            String currency,
            double price,
            String description,
            List<MultipartFile> images
    ) {
        try {

            // BASIC VALIDATION
            if (name == null || name.isBlank()) {
                throw new RuntimeException("Product name is required");
            }

            if (price <= 0) {
                throw new RuntimeException("Product price must be greater than 0");
            }

            if (description == null || description.isBlank()) {
                throw new RuntimeException("Product description is required");
            }

            if (images == null || images.isEmpty()) {
                throw new RuntimeException("At least one product image is required");
            }

            List<String> imageUrls = new ArrayList<>();
            for (MultipartFile file : images) {
                imageUrls.add(cloudinaryService.uploadImage(file));
            }

            Product product = new Product();
            product.setName(name);
            product.setColor(color);
            product.setCurrency(currency);
            product.setPrice(price);
            product.setDescription(description);
            product.setImages(imageUrls);

            return productRepository.save(product);

        } catch (Exception e) {
            throw new RuntimeException("Failed to add product: " + e.getMessage());
        }
    }

    // ================= HOME PAGE PRODUCTS =================
    public List<Product> getAllProducts() {
        try {
            List<Product> products = productRepository.findAll();

            if (products.isEmpty()) {
                throw new RuntimeException("No products available");
            }

            return products;

        } catch (Exception e) {
            throw new RuntimeException("Error fetching products: " + e.getMessage());
        }
    }

    // ================= PRODUCT DETAIL =================
    public Product getProductById(String id) {
        try {

            return productRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));

        } catch (Exception e) {
            throw new RuntimeException("Error fetching product: " + e.getMessage());
        }
    }
    @Autowired
    private ObjectMapper objectMapper;

    public String getEncryptedProductById(String id) {
        try {
            Product product = getProductById(id); // existing method
            String json = objectMapper.writeValueAsString(product);
            return AESUtil.encrypt(json);
        } catch (Exception e) {
            throw new RuntimeException("Error encrypting product data: " + e.getMessage());
        }
    }
    // ================= UPDATE PRODUCT =================
    public Product updateProduct(
            String productId,
            String name,
            String color,
            String currency,
            Double price,
            String description,
            List<MultipartFile> images
    ) {
        try {

            Product product = productRepository.findById(productId)
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            // 🔹 Update only if value present
            if (name != null && !name.isBlank()) {
                product.setName(name);
            }

            if (color != null) {
                product.setColor(color);
            }

            if (currency != null && !currency.isBlank()) {
                product.setCurrency(currency);
            }

            if (price != null && price > 0) {
                product.setPrice(price);
            }

            if (description != null && !description.isBlank()) {
                product.setDescription(description);
            }

            // 🔹 Images update (optional)
            if (images != null && !images.isEmpty()) {
                List<String> imageUrls = new ArrayList<>();
                for (MultipartFile file : images) {
                    imageUrls.add(cloudinaryService.uploadImage(file));
                }
                product.setImages(imageUrls);
            }

            return productRepository.save(product);

        } catch (Exception e) {
            throw new RuntimeException("Failed to update product: " + e.getMessage());
        }
    }
    // ================= DELETE PRODUCT =================
    public void deleteProduct(String id){
        if(!productRepository.existsById(id)){
            throw new RuntimeException("Product not found by id: " + id);
        }
        productRepository.deleteById(id);
    }
}
