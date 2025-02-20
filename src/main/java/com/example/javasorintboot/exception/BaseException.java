package com.example.javasorintboot.exception;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BaseException extends RuntimeException {
    private Integer code;
    private String message;
    private Boolean result;

    public BaseException(String message) {
        super(message);
        this.message = message;
    }

    public BaseException(Integer code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

}
