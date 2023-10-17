package com.junfeng.sample.config;

import com.junfeng.sample.models.Customer;
import com.junfeng.sample.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class CustomerConfig {

    @Bean
    CommandLineRunner commandLineRunner(CustomerRepository repository){
        return args -> {
            Customer adam = new Customer(
                    "Adam",
                    "adam@gmail.com",
                    "0123456789",
                    LocalDate.now()
            );
            Customer john = new Customer(
                    "John",
                    "john@gmail.com",
                    "0123456789",
                    LocalDate.now()
            );

            repository.saveAll(List.of(adam,john));
        };
    }
}
