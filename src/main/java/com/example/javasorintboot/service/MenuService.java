package com.example.javasorintboot.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.javasorintboot.entity.MenuEnity;
import com.example.javasorintboot.model.MenuModal;
import com.example.javasorintboot.repository.MenuRepository;

@Service
public class MenuService {

    private final MenuRepository menuRepository;
    
    public MenuService(MenuRepository menuRepository){
        this.menuRepository = menuRepository;
    }
    public List<MenuEnity> getAllMenu(){
        
        return menuRepository.findAll();
    }

    public MenuEnity createMenu(MenuModal createMenu){
        MenuEnity AddMenu = new MenuEnity();
        AddMenu.setName(createMenu.getName());
        AddMenu.setPrice(createMenu.getPrice());
        return menuRepository.save(AddMenu);
        
    }

}
