package com.junfeng.sample.dto;

import com.junfeng.sample.entity.Address;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;

public class CustomerRequest {

    /* Validation in customer request
    *  - Name        : Cannot be blank
    *  - Email       : Required to follow email format
    *  - Contact No. : Numeric and cannot be blank
    */

    @NotBlank(message="Customer name cannot be blank")
    private String name;
    @Email (message = "Invalid email address")
    private String email;
    @NotNull
    @Pattern(regexp = "^\\d{10,11}$", message = "Invalid contact no format")
    private String contactNo;
    private LocalDate dob;
    private Address address;

    public CustomerRequest() {
    }

    public CustomerRequest(String name, String email, String contactNo, LocalDate dob, Address address) {
        this.name = name;
        this.email = email;
        this.contactNo = contactNo;
        this.dob = dob;
        this.address = address;
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "CustomerRequest{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", contactNo='" + contactNo + '\'' +
                ", dob=" + dob +
                ", address=" + address +
                '}';
    }
}
