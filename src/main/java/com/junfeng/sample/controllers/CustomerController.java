package com.junfeng.sample.controllers;

//import com.junfeng.sample.repositories.CustomerRepositories;
import com.junfeng.sample.advice.exception.CustomerNotFoundException;
import com.junfeng.sample.advice.exception.DuplicateCustomerException;
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

    @GetMapping
    public ResponseEntity<List<Customer>> getCustomers(){

        return ResponseEntity.ok(customerService.getCustomers());
    }

    @PostMapping
    public ResponseEntity<Customer> registerNewCustomer(@RequestBody @Valid CustomerRequest customer) throws DuplicateCustomerException {

        return new ResponseEntity<>(customerService.addNewCustomer(customer), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "{customerId}")
    public void deleteCustomer(@PathVariable("customerId") Long customerId) throws CustomerNotFoundException {
        customerService.deleteCustomer(customerId);
    }

    @PutMapping(path = "{customerId}")
    public void updateCustomer(
            @PathVariable("customerId") Long customerId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email
    ){
        customerService.updateCustomer(customerId,name,email);
    }

    @GetMapping("/list")
    public ResponseEntity<Page<Customer>> getCustomerList(@RequestParam(required = true) int page){

        return ResponseEntity.ok(customerService.getCustomerList(page));
    }
}
