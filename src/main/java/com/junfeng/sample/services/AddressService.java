package com.junfeng.sample.services;


import com.junfeng.sample.entity.Address;
import com.junfeng.sample.entity.Customer;
import com.junfeng.sample.repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    private final AddressRepository addressRepository;

    @Autowired
    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    //Function to add customer address details to database
    public Address addAddress(Address address){
        return this.addressRepository.save(address);
    }

    //Function to delete customer address from database by customer ID
    public void deleteAddress(Customer customer){
        addressRepository.deleteByCustomer(customer);
    }
}
