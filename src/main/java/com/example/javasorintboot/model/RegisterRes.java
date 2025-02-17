package com.example.javasorintboot.model;

import lombok.Data;

@Data
public class RegisterRes {

    private String email;
    private String name;
    private String phone;
    private String password;
    private String confrim_password;
    private String role;

}
