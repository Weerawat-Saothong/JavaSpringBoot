package com.example.javasorintboot.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Response<T> {

    private boolean result;
    private String message;
    private T data;
    private Number status;
    private T error;
    private String token;
}
