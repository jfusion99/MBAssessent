package com.junfeng.sample.services;

import com.junfeng.sample.advice.exception.CustomerNotFoundException;
import com.junfeng.sample.advice.exception.DuplicateCustomerException;
import com.junfeng.sample.advice.exception.InvalidAddressException;
import com.junfeng.sample.advice.exception.PageNotFoundException;
import com.junfeng.sample.dto.CustomerRequest;
import com.junfeng.sample.entity.Address;
import com.junfeng.sample.entity.Customer;
import com.junfeng.sample.repositories.CustomerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final AddressService addressService;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, AddressService addressService) {
        this.customerRepository = customerRepository;
        this.addressService = addressService;
    }

    //Function to fetch all customers from database
    @Transactional(rollbackOn = Exception.class)
    public List<Customer> getCustomers(){
        return customerRepository.findAll();
    }

    //Function to create new customer record in database
    @Transactional(rollbackOn = Exception.class)
    public Customer addNewCustomer(CustomerRequest customerRequest) throws DuplicateCustomerException, InvalidAddressException {

        Customer customer = new Customer(customerRequest.getName(), customerRequest.getEmail(),
                customerRequest.getContactNo(),customerRequest.getDob());

        Optional<Customer> customerOptional =
                customerRepository.findCustomerByEmail(customer.getEmail());

        if(customerOptional.isPresent()){
            throw new DuplicateCustomerException("Email had been registered");
        }

        //Save customer details
        Customer addCust = customerRepository.save(customer);

        //Save customer address
        Address address = customerRequest.getAddress();
        if(address == null){
            throw new InvalidAddressException("Invalid address");
        }
        address.setCustomer(customer);

        this.addressService.addAddress(address);

        return addCust;
    }

    //Function to delete customer based on customer ID
    @Transactional(rollbackOn = Exception.class)
    public void deleteCustomer(Long customerId) throws CustomerNotFoundException {

        Customer customer = customerRepository.findById(customerId).orElseThrow(
                ()-> new CustomerNotFoundException("Customer with ID " + customerId + " does not exist")
        );

        //Delete address of customer with foreign connection
        addressService.deleteAddress(customer);
        //Delete customer details
        customerRepository.deleteById(customerId);
    }

    //Function to update customer details
    @Transactional(rollbackOn = Exception.class)
    public Customer updateCustomer(Long customerId, String name, String email) throws CustomerNotFoundException, DuplicateCustomerException {

        Customer customer = customerRepository.findById(customerId).orElseThrow(
                ()-> new CustomerNotFoundException("Customer with ID " + customerId + " does not exist")
        );

        if(name != null && !name.isEmpty() && !Objects.equals(customer.getName(),name)){
            customer.setName(name);
        }

        if(email != null && !email.isEmpty() && !Objects.equals(customer.getEmail(),email)){
            Optional<Customer> customerOptional =
                    customerRepository.findCustomerByEmail(email);
            if(customerOptional.isPresent()){
                throw new DuplicateCustomerException("Email had been registered");
            }
            customer.setEmail(email);
        }

        return customer;
    }

    //Function to get customer list by pagination
    @Transactional(rollbackOn = Exception.class)
    public Page<Customer> getCustomerList(int pageNumber) throws PageNotFoundException {

        //Fixed each page will have maximum 10 customers data
        final int size = 10;

        // Pageable object
        Pageable pageable = PageRequest.ofSize(size);
        Page<Customer> page = customerRepository.findAll(pageable);

        //Total Page
        int totalPage = page.getTotalPages();

        if(pageNumber > totalPage){
            throw new PageNotFoundException("Page number have no data.");
        }else{
            pageable = PageRequest.of(pageNumber-1,size);
            page = customerRepository.findAll(pageable);
        }

        return page;
    }
}
