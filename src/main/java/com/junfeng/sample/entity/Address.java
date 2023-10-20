package com.junfeng.sample.entity;


import jakarta.persistence.*;

@Entity
@Table (name="ADDRESS_INFO")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String unitNo;
    private String address;
    @OneToOne(cascade = CascadeType.ALL)
    private Customer customer;

    public Address() {
    }

    public Address(Long id, String unitNo, String address, Customer customer) {
        this.id = id;
        this.unitNo = unitNo;
        this.address = address;
        this.customer = customer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getUnitNo() {
        return unitNo;
    }

    public void setUnitNo(String unitNo) {
        this.unitNo = unitNo;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", unitNo='" + unitNo + '\'' +
                ", address='" + address + '\'' +
                ", customer=" + customer +
                '}';
    }
}
