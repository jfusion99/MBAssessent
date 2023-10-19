//package com.junfeng.sample.entity;
//
//
//import jakarta.persistence.*;
//
//@Entity
//@Table (name="ADD_INFO")
//public class Address {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;
//    private String address;
//    private Customer customer;
//
//    public Address() {
//    }
//
//    public Address(Long id, String address, Customer customer) {
//        this.id = id;
//        this.address = address;
//        this.customer = customer;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getAddress() {
//        return address;
//    }
//
//    public void setAddress(String address) {
//        this.address = address;
//    }
//
//    public Customer getCustomer() {
//        return customer;
//    }
//
//    public void setCustomer(Customer customer) {
//        this.customer = customer;
//    }
//
//    @Override
//    public String toString() {
//        return "Address{" +
//                "id=" + id +
//                ", address='" + address + '\'' +
//                ", customer=" + customer +
//                '}';
//    }
//}
