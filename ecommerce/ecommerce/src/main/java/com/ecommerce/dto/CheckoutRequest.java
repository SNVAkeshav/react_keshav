package com.ecommerce.dto;

import lombok.Data;
import java.util.List;

@Data
public class CheckoutRequest {

    private String userId;
    private String addressId;
    private List<CheckoutItem> items;
}
