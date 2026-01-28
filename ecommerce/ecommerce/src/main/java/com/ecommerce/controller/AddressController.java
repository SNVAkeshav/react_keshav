package com.ecommerce.controller;

import com.ecommerce.model.Address;
import com.ecommerce.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    // 1️⃣ Add new address
    @PostMapping("/add")
    public ResponseEntity<?> addAddress(@RequestBody Address address) {
        return ResponseEntity.ok(addressService.save(address));
    }

    // 2️⃣ Get all addresses of user
    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getUserAddresses(@PathVariable String userId) {
        return ResponseEntity.ok(addressService.getByUserId(userId));
    }

    // 3️⃣ Get address by id (Buy Now)
    @GetMapping("/{addressId}")
    public ResponseEntity<?> getAddress(@PathVariable String addressId) {
        return ResponseEntity.ok(addressService.getById(addressId));
    }
    // 4️⃣ UPDATE ADDRESS
    @PutMapping("/{addressId}")
    public ResponseEntity<?> updateAddress(
            @PathVariable String addressId,
            @RequestBody Address address
    ) {
        return ResponseEntity.ok(
                addressService.updateAddress(addressId, address)
        );
    }

    // 5️⃣ DELETE ADDRESS
    @DeleteMapping("/{addressId}")
    public ResponseEntity<?> deleteAddress(@PathVariable String addressId) {
        addressService.deleteAddress(addressId);
        return ResponseEntity.ok("Address deleted successfully");
    }
}
