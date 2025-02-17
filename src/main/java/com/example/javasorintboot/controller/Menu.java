package com.example.javasorintboot.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.javasorintboot.business.MenuBusiness;
import com.example.javasorintboot.entity.MenuEnity;
import com.example.javasorintboot.model.Response;

@RestController
@CrossOrigin(origins = "*")

public class Menu {


    // Example: @GetMapping("/menu")
    private final MenuBusiness business;

    public Menu(MenuBusiness business) {
        this.business = business;
    }

    @GetMapping("/menu")
    public Response<List<MenuEnity>> getMethodName() {
        return business.getMenu();
    }




}
