package com.junfeng.sample.advice;

import com.junfeng.sample.advice.exception.CustomerNotFoundException;
import com.junfeng.sample.advice.exception.DuplicateCustomerException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String,String> handleInvalidArgument(MethodArgumentNotValidException ex){
        Map<String,String> errorMap = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(
                error->{
                    errorMap.put(error.getField(),error.getDefaultMessage());
                }
        );

        return errorMap;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(CustomerNotFoundException.class)
    public Map<String,String> handleBusinessException(CustomerNotFoundException ex){
        Map<String,String> errorMap = new HashMap<>();
        errorMap.put("Error Message",ex.getMessage());

        return errorMap;
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DuplicateCustomerException.class)
    public Map<String,String> handleDuplicateException(DuplicateCustomerException ex){
        Map<String,String> errorMap = new HashMap<>();
        errorMap.put("Error Input",ex.getMessage());

        return errorMap;
    }
}
