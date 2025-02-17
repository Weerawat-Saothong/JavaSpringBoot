package com.example.javasorintboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.javasorintboot.entity.MenuEnity;

@Repository
public interface MenuRepository extends JpaRepository<MenuEnity,String> {

    
}
