package com.example.javasorintboot.business;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.javasorintboot.entity.LoginEntity;
import com.example.javasorintboot.entity.table;
import com.example.javasorintboot.model.LoginRes;
import com.example.javasorintboot.model.Response;
import com.example.javasorintboot.service.LoginService;

@Service
public class LoginBusiness {

    private final LoginService loginService;

    public LoginBusiness(LoginService LoginService) {
        this.loginService = LoginService;
    }

    public Response<String> Login(LoginRes login) {
        Optional<LoginEntity> user = loginService.LogingMyEmailAndPassword(login.getEmail(), login.getPassword());

        Response<String> response = new Response<>();
        if (user.isPresent()) {
            response.setResult(true);
            response.setMessage("success");
            response.setData(user.get().getEmail());
            response.setStatus(200);
        } else {
            response.setResult(false);
            response.setMessage("Invalid email or password");
            response.setStatus(401);
        }

        return response;
    }

    public Response<List<LoginEntity>> getLogin() {

        List<LoginEntity> AllUser = loginService.FindAll();
        Response<List<LoginEntity>> response = new Response<>();
        response.setResult(true);
        response.setMessage("success");
        response.setData(AllUser);
        response.setStatus(200);
        return response;
    }

    public Response<String> LoginByQRcodeBusiness(String qrCode) {
        table table = loginService.findByID(qrCode);
        Response<String> response = new Response<>();

        if (table == null) {
            response.setResult(false);
            response.setMessage("Invalid QR code Please try again");
            response.setStatus(404);
        } else {
            response.setResult(true);
            response.setMessage("success");
            response.setData(table.getName());
            response.setStatus(200);
        }

        return response;
    }

    public Response<List<table>> GetAllId() {
        List<table> table = loginService.findAll();
        Response<List<table>> response = new Response<>();

        if (table == null) {
            response.setResult(false);
            response.setMessage("Invalid QR code Please try again");
            response.setStatus(404);
        } else {
            response.setResult(true);
            response.setMessage("success");
            response.setData(table);
            response.setStatus(200);
        }

        return response;
    }

    public Response<List<table>> getAllID() {
        List<table> table = loginService.findAll();
        Response<List<table>> response = new Response<>();
        if (table == null) {
            response.setResult(false);
            response.setMessage("Not Found");
            response.setStatus(404);
        } else {
            response.setResult(true);
            response.setMessage("success");
            response.setData(table);
            response.setStatus(200);
        }
        return response;
    }

}
