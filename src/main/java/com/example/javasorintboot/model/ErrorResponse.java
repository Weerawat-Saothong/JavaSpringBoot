package com.example.javasorintboot.model;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.javasorintboot.exception.BaseException;

import lombok.Data;

@RestControllerAdvice
public class ErrorResponse {

    @ExceptionHandler(BaseException.class)
    public ErrorResponseAll handleException(BaseException e) {
        ErrorResponseAll errorResponse = new ErrorResponseAll();
        System.out.println(e.getCode());
        errorResponse.setMessage(e.getMessage());
        errorResponse.setStatus(e.getCode());
        return errorResponse;
    }

    @Data
    public static class ErrorResponseAll {
        private String message;
        private int status;
    }
}
