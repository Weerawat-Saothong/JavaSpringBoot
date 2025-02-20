package com.example.javasorintboot.exception;

public class ResponseException extends BaseException {
    public ResponseException(int code, String message) {
        super(code, message);
    }

    public static ResponseException responseError(int code, String message) {
        return new ResponseException(code, message);
    }
}
