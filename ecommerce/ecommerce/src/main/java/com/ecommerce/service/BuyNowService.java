package com.ecommerce.service;

import com.ecommerce.dto.BuyNowRequest;
import com.ecommerce.model.Order;
import com.ecommerce.model.Product;
import com.ecommerce.repository.OrderRepository;
import com.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class BuyNowService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    public Map<String, Object> createBuyNowOrder(BuyNowRequest request) {

        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        double totalAmount = product.getPrice() * request.getQuantity();

        Order order = new Order();
        order.setUserId(request.getUserId());
        order.setProductId(product.getId());
        order.setQuantity(request.getQuantity());
        order.setCurrency(product.getCurrency());
        order.setAmount(totalAmount);
        order.setStatus("PENDING");
        order.setCreatedAt(LocalDateTime.now());

        orderRepository.save(order);

        // 👇 Payment Gateway Order (dummy for now)
        String paymentOrderId = "PAY_" + UUID.randomUUID();

        order.setPaymentOrderId(paymentOrderId);
        orderRepository.save(order);

        Map<String, Object> response = new HashMap<>();
        response.put("orderId", order.getId());
        response.put("paymentOrderId", paymentOrderId);
        response.put("currency", order.getCurrency());
        response.put("amount", totalAmount);
        response.put("productName", product.getName());

        return response;
    }
}
