package com.essies.ecommerce.service;

import com.essies.ecommerce.data.model.Address;
import com.essies.ecommerce.data.model.User;

public interface AddressService {
    Address addAddress(Address address, User user);

    void deleteAll();
}
