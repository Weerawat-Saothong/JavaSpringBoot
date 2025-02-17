package com.example.javasorintboot.business;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.javasorintboot.entity.MenuEnity;
import com.example.javasorintboot.model.MenuModal;
import com.example.javasorintboot.model.Response;
import com.example.javasorintboot.service.MenuService;

@Service

public class MenuBusiness {

    private final MenuService menuService;

    public MenuBusiness(MenuService menuService) {
        this.menuService = menuService;
    }

    public Response<List<MenuEnity>> getMenu() {
        List<MenuEnity> AllMenu = menuService.getAllMenu();

        Response<List<MenuEnity>> response = new Response<>();
        if (AllMenu.isEmpty()) {
            response.setResult(false);
            response.setMessage("Menu is empty");
            response.setStatus(HttpStatus.BAD_REQUEST.value());
        } else {
            response.setResult(true);
            response.setMessage("success");
            response.setData(AllMenu);
            response.setStatus(HttpStatus.OK.value());
        }

        return response;

    }

    public Response<String> createMenu(MenuModal createMenu) {

        Response<String> response = new Response<>();
        if (createMenu == null) {
            response.setResult(false);
            response.setMessage("Menu data is null. Please provide valid input.");
            response.setStatus(HttpStatus.BAD_REQUEST.value());
        } else {
            menuService.createMenu(createMenu);
            response.setResult(true);
            response.setMessage("Menu created successfully");
            response.setStatus(HttpStatus.OK.value());
        }
        return response;
    }

}
