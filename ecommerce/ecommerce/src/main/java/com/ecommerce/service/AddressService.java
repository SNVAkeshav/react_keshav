package com.ecommerce.service;


import com.ecommerce.model.Address;
import com.ecommerce.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    public Address save(Address address) {
        return addressRepository.save(address);
    }

    public List<Address> getByUserId(String userId) {
        return addressRepository.findByUserId(userId);
    }

    public Address getById(String addressId) {
        return addressRepository.findById(addressId)
                .orElseThrow(() -> new RuntimeException("Address not found"));
    }
    // UPDATE ADDRESS
    public Address updateAddress(String addressId, Address updated) {

        Address existing = getById(addressId);

        if (updated.getFullName() != null)
            existing.setFullName(updated.getFullName());

        if (updated.getMobileNumber() != null)
            existing.setMobileNumber(updated.getMobileNumber());

        if (updated.getAddressLine() != null)
            existing.setAddressLine(updated.getAddressLine());

        if (updated.getLandmark() != null)
            existing.setLandmark(updated.getLandmark());

        if (updated.getCity() != null)
            existing.setCity(updated.getCity());

        if (updated.getState() != null)
            existing.setState(updated.getState());

        if (updated.getCountry() != null)
            existing.setCountry(updated.getCountry());

        if (updated.getPincode() != null)
            existing.setPincode(updated.getPincode());

        existing.setDefault(updated.isDefault());

        return addressRepository.save(existing);
    }

    // DELETE ADDRESS
    public void deleteAddress(String addressId) {
        Address address = getById(addressId);
        addressRepository.delete(address);
    }
}
