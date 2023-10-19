package com.junfeng.sample.advice.exception;

public class DuplicateCustomerException extends Exception{

    public DuplicateCustomerException(String message) {
        super(message);
    }
}
