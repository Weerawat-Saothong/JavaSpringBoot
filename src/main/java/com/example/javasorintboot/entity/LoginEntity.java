package com.example.javasorintboot.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@Table(name = "User_login")
@EqualsAndHashCode(callSuper = true)
public class LoginEntity extends BaseEnity {

    @Column(name = "email", nullable = false, length = 60, unique = true)
    private String email;

    @JsonIgnore
    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "name", nullable = false)
    private String name;
    
    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "roleName", nullable = false)
    private String roleName;

}
