package com.junfeng.sample.repositories;

import com.junfeng.sample.entity.Address;
import com.junfeng.sample.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address,Long> {

    void deleteByCustomer(Customer customer);
}
