package com.junfeng.sample.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table (name="CUSTOMER_INFO")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String email;
    private String contactNo;
    private LocalDate dob;

    public Customer() {
    }

    public Customer(Long id, String name, String email, String contactNo, LocalDate dob) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.contactNo = contactNo;
        this.dob = dob;
    }

    public Customer(String name, String email, String contactNo, LocalDate dob) {
        this.name = name;
        this.email = email;
        this.contactNo = contactNo;
        this.dob = dob;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", contactNo='" + contactNo + '\'' +
                ", dob=" + dob +
                '}';
    }
}
