package com.example.javasorintboot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.example.javasorintboot.model.ErrorResponse;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CustomHandlerExceptionResolver implements HandlerExceptionResolver {
  @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        ErrorResponse errorResponse = new ErrorResponse();

        if (ex instanceof ResponseException) {
            errorResponse.setMessage(ex.getMessage());
            errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        } else {
            errorResponse.setMessage("เกิดข้อผิดพลาดที่ไม่คาดคิด.");
            errorResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }

        response.setStatus(HttpStatus.BAD_REQUEST.value());
        return new ModelAndView("jsonView", "error", errorResponse);
    }
}
