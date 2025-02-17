package com.example.javasorintboot.business;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.javasorintboot.entity.LoginEntity;
import com.example.javasorintboot.entity.table;
import com.example.javasorintboot.model.LoginRes;
import com.example.javasorintboot.model.RegisterRes;
import com.example.javasorintboot.model.Response;
import com.example.javasorintboot.service.LoginService;

@Service
public class LoginBusiness {

    private final LoginService loginService;

    public LoginBusiness(LoginService LoginService) {
        this.loginService = LoginService;
    }

    // validate emaill

    public boolean isValidEmail(String email) {
        if (email == null) {
            return false;
        }
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email.matches(emailRegex);
    }

    // hash password
    private String hashPassword(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        return passwordEncoder.encode(password);
    }

    // check password

    private Boolean checkPassword(String passwordNotHash, String passwordOnDatabase) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(passwordNotHash, passwordOnDatabase);
    }

    public Response<String> Login(LoginRes login) {

        Response<String> response = new Response<>();
        if (!isValidEmail(login.getEmail())) {
            response.setResult(false);
            response.setMessage("Invalid email");
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            return response;
        } else if (login.getPassword() == null) {
            response.setResult(false);
            response.setMessage("Invalid password");
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            return response;
        } else {
            Optional<LoginEntity> user = loginService.LogingMyEmail(login.getEmail());
            if (!checkPassword(login.getPassword(), user.get().getPassword())) {
                response.setResult(false);
                response.setMessage("Invalid email or password");
                response.setStatus(HttpStatus.BAD_REQUEST.value());
                return response;
            } else if (!user.isPresent()) {
                response.setResult(false);
                response.setMessage("User not found");
                response.setStatus(HttpStatus.NOT_FOUND.value());
                return response;
            } else {
                response.setResult(true);
                response.setMessage("Login success");
                response.setData(user.get().getEmail());
                response.setStatus(HttpStatus.OK.value());
                return response;
            }
        }
    }

    public Response<List<LoginEntity>> getLogin() {

        List<LoginEntity> AllUser = loginService.FindAll();
        Response<List<LoginEntity>> response = new Response<>();
        response.setResult(true);
        response.setMessage("success");
        response.setData(AllUser);
        response.setStatus(HttpStatus.OK.value());
        return response;
    }

    public Response<String> LoginByQRcodeBusiness(String qrCode) {
        table table = loginService.findByID(qrCode);
        Response<String> response = new Response<>();

        if (table == null) {
            response.setResult(false);
            response.setMessage("Invalid QR code Please try again");
            response.setStatus(HttpStatus.NOT_FOUND.value());
        } else {
            response.setResult(true);
            response.setMessage("success");
            response.setData(table.getName());
            response.setStatus(HttpStatus.OK.value());
        }

        return response;
    }

    public Response<List<table>> GetAllId() {
        List<table> table = loginService.findAll();
        Response<List<table>> response = new Response<>();
        if (table == null) {
            response.setResult(false);
            response.setMessage("Invalid QR code Please try again");
            response.setStatus(HttpStatus.NOT_FOUND.value());
        } else {
            response.setResult(true);
            response.setMessage("success");
            response.setData(table);
            response.setStatus(HttpStatus.OK.value());
        }

        return response;
    }

    public Response<List<table>> getAllID() {
        List<table> table = loginService.findAll();
        Response<List<table>> response = new Response<>();
        if (table == null) {
            response.setResult(false);
            response.setMessage("Not Found");
            response.setStatus(HttpStatus.NOT_FOUND.value());
        } else {
            response.setResult(true);
            response.setMessage("success");
            response.setData(table);
            response.setStatus(HttpStatus.OK.value());
        }
        return response;
    }

    // Logic for Register

    public Response<String> Reginster(RegisterRes register) {

        Response<String> response = new Response<>();
        if (register == null) {
            response.setResult(false);
            response.setMessage("Register data is null. Please provide valid input.");
            response.setStatus(HttpStatus.BAD_REQUEST.value());

        } else if (!register.getPassword().equals(register.getConfrim_password())) {
            response.setResult(false);
            response.setMessage("Password and Confirm Password does not match.");
            response.setStatus(HttpStatus.BAD_REQUEST.value());

        } else if (!isValidEmail(register.getEmail())) {
            response.setResult(false);
            response.setMessage("Invalid Email");
            response.setStatus(HttpStatus.BAD_REQUEST.value());

        } else {
            register.setPassword(hashPassword(register.getPassword()));
            loginService.ReginsterService(register);
            response.setResult(true);
            response.setMessage("Register successfully");
            response.setStatus(HttpStatus.OK.value());
        }
        return response;
    }

}
