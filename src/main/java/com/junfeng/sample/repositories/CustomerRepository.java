package com.junfeng.sample.repositories;

import com.junfeng.sample.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {

    //@Query("SELECT s FROM Student s WHERE s.email=?1")
    Optional<Customer> findCustomerByEmail(String email);
}
