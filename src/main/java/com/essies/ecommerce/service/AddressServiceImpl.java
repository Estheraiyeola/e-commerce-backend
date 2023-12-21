package com.essies.ecommerce.service;

import com.essies.ecommerce.data.model.Address;
import com.essies.ecommerce.data.model.User;
import com.essies.ecommerce.data.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.lang.System.exit;

@Service
public class AddressServiceImpl implements AddressService{
    @Autowired
    private AddressRepository addressRepository;
    @Override
    public Address addAddress(Address address, User user) {
        Address newAddress = new Address();
        newAddress.setStreetNumber(address.getStreetNumber());
        newAddress.setStreetName(address.getStreetName());
        newAddress.setCity(address.getCity());
        newAddress.setCountry(address.getCountry());
        newAddress.setLgaName(address.getLgaName());
        newAddress.setPostalCode(address.getPostalCode());
        newAddress.setUser(user);
        return newAddress;
    }

    @Override
    public void deleteAll() {
        addressRepository.deleteAll();
    }


}
