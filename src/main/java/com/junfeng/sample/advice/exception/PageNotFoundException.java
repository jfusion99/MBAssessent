package com.junfeng.sample.advice.exception;

public class PageNotFoundException extends Exception{

    public PageNotFoundException(String message) {
        super(message);
    }
}
