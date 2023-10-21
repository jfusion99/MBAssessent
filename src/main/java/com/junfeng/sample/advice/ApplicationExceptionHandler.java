package com.junfeng.sample.advice;

import com.junfeng.sample.advice.exception.CustomerNotFoundException;
import com.junfeng.sample.advice.exception.DuplicateCustomerException;
import com.junfeng.sample.advice.exception.InvalidAddressException;
import com.junfeng.sample.advice.exception.PageNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ApplicationExceptionHandler {

    //Handle exception where request body of API failed in validation
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

    //Handle exception where API fetching customer that is no exist
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(CustomerNotFoundException.class)
    public Map<String,String> handleBusinessException(CustomerNotFoundException ex){
        Map<String,String> errorMap = new HashMap<>();
        errorMap.put("Error Message",ex.getMessage());

        return errorMap;
    }

    //Handle exception assertion error
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(AssertionError.class)
    public Map<String,String> handleAssertionException(AssertionError ex){
        Map<String,String> errorMap = new HashMap<>();
        errorMap.put("Error Message",ex.getMessage());

        return errorMap;
    }

    //Handle exception where API creating duplicate customer
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DuplicateCustomerException.class)
    public Map<String,String> handleDuplicateException(DuplicateCustomerException ex){
        Map<String,String> errorMap = new HashMap<>();
        errorMap.put("Error Input",ex.getMessage());

        return errorMap;
    }

    //Handle exception where API fetch page which not exist
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(PageNotFoundException.class)
    public Map<String,String> handlePageNotFoundException(PageNotFoundException ex){
        Map<String,String> errorMap = new HashMap<>();
        errorMap.put("Error Input",ex.getMessage());

        return errorMap;
    }

    //Handle exception where API detect customer address is null
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidAddressException.class)
    public Map<String,String> handleInvalidAddressException(InvalidAddressException ex){
        Map<String,String> errorMap = new HashMap<>();
        errorMap.put("Error Input",ex.getMessage());

        return errorMap;
    }
}
