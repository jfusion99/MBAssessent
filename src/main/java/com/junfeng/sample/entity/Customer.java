package com.junfeng.sample.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table
public class Customer {

    @Id
    @SequenceGenerator(
            name = "cust_sequence",
            sequenceName = "cust_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "cust_sequence"
    )

    private Long id;
    private String name;
    private String email;
    private String contactNo;
    private LocalDate dateJoined;

    public Customer() {
    }

    public Customer(Long id, String name, String email, String contactNo, LocalDate dateJoined) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.contactNo = contactNo;
        this.dateJoined = dateJoined;
    }

    public Customer(String name, String email, String contactNo, LocalDate dateJoined) {
        this.name = name;
        this.email = email;
        this.contactNo = contactNo;
        this.dateJoined = dateJoined;
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

    public LocalDate getDateJoined() {
        return dateJoined;
    }

    public void setDateJoined(LocalDate dateJoined) {
        this.dateJoined = dateJoined;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", contactNo='" + contactNo + '\'' +
                ", dateJoined=" + dateJoined +
                '}';
    }
}
