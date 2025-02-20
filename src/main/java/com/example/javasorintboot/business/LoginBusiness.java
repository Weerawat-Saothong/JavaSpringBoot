package com.example.javasorintboot.business;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.javasorintboot.entity.LoginEntity;
import com.example.javasorintboot.entity.table;
import com.example.javasorintboot.exception.ResponseException;
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

    public Response<String> Login(LoginRes login) throws ResponseException {

        Response<String> response = new Response<>();
        if (!isValidEmail(login.getEmail())) {
            throw ResponseException.responseError(HttpStatus.BAD_REQUEST.value(), "Invalid email");
        } else if (login.getPassword() == null) {
            throw ResponseException.responseError(HttpStatus.BAD_REQUEST.value(), "Invalid password");
        } else {
            Optional<LoginEntity> user = loginService.LogingMyEmail(login.getEmail());
            if (!checkPassword(login.getPassword(), user.get().getPassword())) {
                throw ResponseException.responseError(HttpStatus.BAD_REQUEST.value(), "Invalid email or password");
            } else if (!user.isPresent()) {

                throw ResponseException.responseError(HttpStatus.NOT_FOUND.value(), "User not found");
            } else {
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

    public Response<String> LoginByQRcodeBusiness(String qrCode) throws ResponseException {
        table table = loginService.findByID(qrCode);
        Response<String> response = new Response<>();

        if (table == null) {
            throw ResponseException.responseError(HttpStatus.NOT_FOUND.value(), "Invalid QR code Please try again");
        } else {
            response.setResult(true);
            response.setMessage("success");
            response.setData(table.getName());
            response.setStatus(HttpStatus.OK.value());
        }

        return response;
    }

    // Logic for Register

    public Response<String> Reginster(RegisterRes register) throws ResponseException {

        Response<String> response = new Response<>();
        if (register == null) {

            throw ResponseException.responseError(HttpStatus.BAD_REQUEST.value(),
                    "Register data is null. Please provide valid input.");

        } else if (!register.getPassword().equals(register.getConfrim_password())) {

            throw ResponseException.responseError(HttpStatus.BAD_REQUEST.value(),
                    "Password and Confirm Password does not match.");

        } else if (!isValidEmail(register.getEmail())) {

            throw ResponseException.responseError(HttpStatus.BAD_REQUEST.value(), "Invalid Email");

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
