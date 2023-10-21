package com.junfeng.sample.controllers;

import com.junfeng.sample.advice.exception.CustomerNotFoundException;
import com.junfeng.sample.advice.exception.DuplicateCustomerException;
import com.junfeng.sample.advice.exception.InvalidAddressException;
import com.junfeng.sample.advice.exception.PageNotFoundException;
import com.junfeng.sample.dto.CustomerRequest;
import com.junfeng.sample.entity.Customer;
import com.junfeng.sample.services.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/customer")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    //Function to fetch all customers
    @GetMapping
    public ResponseEntity<List<Customer>> getCustomers(){

        return ResponseEntity.ok(customerService.getCustomers());
    }

    //Function to add new customer
    @PostMapping
    public ResponseEntity<Customer> registerNewCustomer(@RequestBody @Valid CustomerRequest customer) throws DuplicateCustomerException, InvalidAddressException {

        return new ResponseEntity<>(customerService.addNewCustomer(customer), HttpStatus.CREATED);
    }

    //Function to delete existing customer
    @DeleteMapping(path = "{customerId}")
    public String deleteCustomer(@PathVariable("customerId") Long customerId) throws CustomerNotFoundException {
        customerService.deleteCustomer(customerId);

        return "{}";
    }

    //Function to update customer's details
    @PutMapping(path = "{customerId}")
    public Customer updateCustomer(
            @PathVariable("customerId") Long customerId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email
    ) throws CustomerNotFoundException, DuplicateCustomerException {
        return customerService.updateCustomer(customerId,name,email);
    }

    //Function to fetch customer by pagination (10 customer per page)
    @GetMapping("/list")
    public ResponseEntity<Page<Customer>> getCustomerList(@RequestParam(required = true) int page) throws PageNotFoundException {

        return ResponseEntity.ok(customerService.getCustomerList(page));
    }
}
