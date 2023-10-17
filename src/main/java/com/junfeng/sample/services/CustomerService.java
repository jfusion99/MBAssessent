package com.junfeng.sample.services;

import com.junfeng.sample.models.Customer;
import com.junfeng.sample.repositories.CustomerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getCustomers(){
        return customerRepository.findAll();
    }

    public void addNewCustomer(Customer customer) {
        Optional<Customer> customerOptional =
                customerRepository.findCustomerByEmail(customer.getEmail());

        if(customerOptional.isPresent()){
            throw new IllegalStateException("Email had been registered");
        }
        customerRepository.save(customer);
    }

    public void deleteCustomer(Long customerId) {
        boolean exists = customerRepository.existsById(customerId);

        if(!exists){
            throw new IllegalStateException("Customer with ID " + customerId + " does not exist");
        }
        customerRepository.deleteById(customerId);
    }

    @Transactional
    public void updateCustomer(Long customerId, String name, String email) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(
                ()-> new IllegalStateException("Customer with ID " + customerId + " does not exist")
        );

        if(name != null && !name.isEmpty() && !Objects.equals(customer.getName(),name)){
            customer.setName(name);
        }

        if(email != null && !email.isEmpty() && !Objects.equals(customer.getEmail(),email)){
            Optional<Customer> customerOptional =
                    customerRepository.findCustomerByEmail(email);
            if(customerOptional.isPresent()){
                throw new IllegalStateException("Email had been registered");
            }
            customer.setEmail(email);
        }
    }
}
