package com.junfeng.sample.services;

import com.junfeng.sample.advice.exception.CustomerNotFoundException;
import com.junfeng.sample.advice.exception.DuplicateCustomerException;
import com.junfeng.sample.dto.CustomerRequest;
import com.junfeng.sample.entity.Customer;
import com.junfeng.sample.repositories.CustomerRepository;
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

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getCustomers(){
        return customerRepository.findAll();
    }

    public Customer addNewCustomer(CustomerRequest customerRequest) throws DuplicateCustomerException {

        Customer customer = new Customer(customerRequest.getName(), customerRequest.getEmail(),
                customerRequest.getContactNo(),customerRequest.getDateJoined());

        Optional<Customer> customerOptional =
                customerRepository.findCustomerByEmail(customer.getEmail());

        if(customerOptional.isPresent()){
            throw new DuplicateCustomerException("Email had been registered");
        }
        return customerRepository.save(customer);
    }

    public void deleteCustomer(Long customerId) throws CustomerNotFoundException {

        boolean exists = customerRepository.existsById(customerId);

        if(!exists){
            throw new CustomerNotFoundException("Customer with ID " + customerId + " does not exist");
        }
        customerRepository.deleteById(customerId);
    }

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

    public Page<Customer> getCustomerList(int pageNumber) {

        int size = 10;

        // Pageable object
        Pageable pageable = PageRequest.ofSize(size);
        Page<Customer> page = customerRepository.findAll(pageable);

        //Total Page
        int totalPage = page.getTotalPages();

        if(pageNumber > totalPage){
            throw new IllegalStateException("Page number have no data.");
        }else{
            pageable = PageRequest.of(pageNumber-1,size);
            page = customerRepository.findAll(pageable);
        }

        return page;
    }
}
