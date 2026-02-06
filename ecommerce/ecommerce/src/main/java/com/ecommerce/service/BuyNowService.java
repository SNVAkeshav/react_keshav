package com.ecommerce.service;


import com.ecommerce.dto.CheckoutItem;
import com.ecommerce.dto.CheckoutRequest;
import com.ecommerce.model.Address;
import com.ecommerce.model.Order;
import com.ecommerce.model.Product;
import com.ecommerce.repository.OrderRepository;
import com.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class BuyNowService {
    @Autowired
    private AddressService addressService;


    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    public Map<String, Object> createOrder(CheckoutRequest request) {
        Address address = addressService.getById(request.getAddressId());

        List<Order> orders = new ArrayList<>();
        double totalPayableAmount = 0;

        String paymentOrderId = "PAY_" + UUID.randomUUID();

        for (CheckoutItem item : request.getItems()) {

            Product product = productRepository.findById(item.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            double amount = product.getPrice() * item.getQuantity();
            totalPayableAmount += amount;

            Order order = new Order();
            order.setUserId(request.getUserId());
            order.setProductId(product.getId());
            order.setQuantity(item.getQuantity());
            order.setCurrency(product.getCurrency());
            order.setAmount(amount);
            order.setStatus("PENDING");
            order.setPaymentOrderId(paymentOrderId);
            order.setAddressId(address.getId());
            order.setCreatedAt(LocalDateTime.now());

            orders.add(order);
        }

        orderRepository.saveAll(orders);

        Map<String, Object> response = new HashMap<>();
        response.put("paymentOrderId", paymentOrderId);
        response.put("totalAmount", totalPayableAmount);
        response.put("orders", orders);

        return response;
    }
}
