package com.junfeng.sample.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;

public class CustomerRequest {

    @NotBlank(message="filter name cannot be blank")
    private String name;
    @Email (message = "Invalid email address")
    private String email;
    @NotNull
    @Pattern(regexp = "^\\d{10}$", message = "Invalid contact no format")
    private String contactNo;
    private LocalDate dateJoined;

    public CustomerRequest() {
    }

    public CustomerRequest(String name, String email, String contactNo, LocalDate dateJoined) {
        this.name = name;
        this.email = email;
        this.contactNo = contactNo;
        this.dateJoined = dateJoined;
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
        return "CustomerRequest{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", contactNo='" + contactNo + '\'' +
                ", dateJoined=" + dateJoined +
                '}';
    }
}
