package com.junfeng.sample.controllers;

//import com.junfeng.sample.repositories.CustomerRepositories;
import com.junfeng.sample.models.Customer;
import com.junfeng.sample.services.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/customer")
public class CustomerController {

    Logger logger = LoggerFactory.getLogger(CustomerController.class);

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public List<Customer> getCustomers(){
        logger.info("[GET] api/v1/customer - Start");

        return customerService.getCustomers();
    }

    @PostMapping
    public void registerNewCustomer(@RequestBody Customer customer){

        logger.info("[POST] api/v1/customer - Start");
        customerService.addNewCustomer(customer);
    }

    @DeleteMapping(path = "{customerId}")
    public void deleteCustomer(@PathVariable("customerId") Long customerId){

        logger.info("[DELETE] api/v1/customer - Start");
        customerService.deleteCustomer(customerId);
    }

    @PutMapping(path = "{customerId}")
    public void updateCustomer(
            @PathVariable("customerId") Long customerId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email
    ){
        logger.info("[PUT] api/v1/customer/"+ String.valueOf(customerId) +" - Start");
        customerService.updateCustomer(customerId,name,email);
    }

    @GetMapping("/list")
    public Page<Customer> getCustomerList(@RequestParam(required = true) int page){

        logger.info("[GET] api/v1/customer/list - Start");
        return customerService.getCustomerList(page);
    }
}
