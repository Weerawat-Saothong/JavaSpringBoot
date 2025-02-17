package com.example.javasorintboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.javasorintboot.entity.LoginEntity;
import com.example.javasorintboot.entity.table;
import com.example.javasorintboot.model.RegisterRes;
import com.example.javasorintboot.repository.LoginRepository;
import com.example.javasorintboot.repository.TableFood;

@Service
public class LoginService {

    private final LoginRepository loginRepository;
    private final TableFood tableFoodRepository;

    public LoginService(LoginRepository loginRepository, TableFood tableFood) {
        this.tableFoodRepository = tableFood;
        this.loginRepository = loginRepository;
    }

    public Optional<LoginEntity> LogingMyEmail(String email) {
        return loginRepository.findByEmail(email);
    }

    public List<LoginEntity> FindAll() {
        return loginRepository.findAll();
    }

    public table findByID(String id) {
        return tableFoodRepository.findById(id).get();
    }
    public List<table> findAll() {
        return tableFoodRepository.findAll();
    }

    public LoginEntity ReginsterService(RegisterRes register) {
        LoginEntity registerService = new LoginEntity();
        registerService.setEmail(register.getEmail());
        registerService.setPassword(register.getPassword());
        registerService.setName(register.getName());
        registerService.setPhone(register.getPhone());
        registerService.setRoleName(register.getRole());

        return loginRepository.save(registerService);
    }

}
