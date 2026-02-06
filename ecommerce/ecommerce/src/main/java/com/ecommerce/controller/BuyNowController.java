package com.ecommerce.controller;

import com.ecommerce.dto.BuyNowRequest;
import com.ecommerce.dto.CheckoutRequest;
import com.ecommerce.model.Address;
import com.ecommerce.model.Order;
import com.ecommerce.repository.OrderRepository;
import com.ecommerce.service.AddressService;
import com.ecommerce.service.BuyNowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api")
//public class BuyNowController {
//
//    @Autowired
//    private OrderRepository orderRepository;
//    @Autowired
//    private BuyNowService buyNowService;
//    @Autowired
//    private AddressService addressService;
//
//    @PostMapping("/buy-now")
//    public ResponseEntity<?> buyNow(@RequestBody BuyNowRequest request) {
//        Address address = addressService.getById(request.getAddressId());
//
//        Order order = new Order();
//        order.setUserId(request.getUserId());
//        order.setProductId(request.getProductId());
//        order.setQuantity(request.getQuantity());
//        order.setCurrency(request.getCurrency());
//        order.setAmount(request.getAmount()* request.getQuantity());
//        order.setStatus("PENDING");
//        order.setAddressId(address.getId());
//        order.setPaymentOrderId( "PAY_" + UUID.randomUUID());
//        order.setCreatedAt(address.getCreatedAt());
//
//        orderRepository.save(order);
//
//        return ResponseEntity.ok(order);
//    }
//}
//@RestController
//@RequestMapping("/api")
public class BuyNowController {

    @Autowired
    private BuyNowService buyNowService;

    @Autowired
    private AddressService addressService;

    @PostMapping("/checkout")
    public ResponseEntity<?> checkout(@RequestBody CheckoutRequest request) {

        // sirf validation ke liye
        addressService.getById(request.getAddressId());

        return ResponseEntity.ok(buyNowService.createOrder(request));
    }
}

