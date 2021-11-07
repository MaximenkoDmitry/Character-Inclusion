package com.bsuir.counter.controllers;

import com.bsuir.counter.exceptions.ServiceException;
import com.bsuir.counter.exceptions.ValidateException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class ExceptionHandler {
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    @org.springframework.web.bind.annotation.ExceptionHandler(ServiceException.class)
    public Object handleServiceException(ServiceException serviceException){
        return Map.of("result", serviceException);
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @org.springframework.web.bind.annotation.ExceptionHandler(ValidateException.class)
    public Object handleValidateException(ValidateException validateException){
        return Map.of("exception", validateException.getMessage());
    }

}
