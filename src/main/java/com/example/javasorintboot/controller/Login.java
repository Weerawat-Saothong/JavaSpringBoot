package com.example.javasorintboot.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.javasorintboot.business.LoginBusiness;
import com.example.javasorintboot.entity.LoginEntity;
import com.example.javasorintboot.entity.table;
import com.example.javasorintboot.model.LoginRes;
import com.example.javasorintboot.model.RegisterRes;
import com.example.javasorintboot.model.Response;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/auth")

public class Login {
    private final LoginBusiness loginBusiness;

    public Login(LoginBusiness LoginBusiness) {
        this.loginBusiness = LoginBusiness;
    }

    @PostMapping("/login")
    public Response<String> postLogin(@RequestBody LoginRes login) {

        return loginBusiness.Login(login);
    }

    @GetMapping("/getLogin")
    public Response<List<LoginEntity>> getMethodName() {
        return loginBusiness.getLogin();
    }

    @GetMapping("scanqrcode/{id}")
    public Response<String> loginByQRcode(@PathVariable String id) {

        return loginBusiness.LoginByQRcodeBusiness(id);
    }

    @GetMapping("scanqrcode")
    public Response<List<table>> getAllID() {
        return loginBusiness.GetAllId();
    }

    @PostMapping("register")
    public Response<String> postMethodName(@RequestBody RegisterRes register) {
        
        
        return loginBusiness.Reginster(register);
    }
    

}
