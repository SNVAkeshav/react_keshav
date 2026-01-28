package com.ecommerce.service;

import com.ecommerce.model.Cart;
import com.ecommerce.model.CartItem;
import com.ecommerce.model.Product;
import com.ecommerce.repository.CartRepository;
import com.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    public Cart addToCart(String userId, String productId, int quantity) {

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Cart cart = cartRepository.findByUserId(userId)
                .orElse(new Cart());

        cart.setUserId(userId);

        boolean productExists = false;

        for (CartItem item : cart.getItems()) {
            if (item.getProductId().equals(productId)) {
                item.setQuantity(item.getQuantity() + quantity);
                productExists = true;
                break;
            }
        }

        if (!productExists) {
            CartItem item = new CartItem();
            item.setProductId(productId);
            item.setProductName(product.getName());
            item.setCurrency(product.getCurrency());
            item.setPrice(product.getPrice());
            item.setQuantity(quantity);
            item.setImage(product.getImages().get(0));

            cart.getItems().add(item);
        }

        return cartRepository.save(cart);
    }

    public Cart getCart(String userId) {
        return cartRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Cart is empty"));
    }
}
