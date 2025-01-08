package com.example.javasorintboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.javasorintboot.entity.table;

@Repository
public interface TableFood extends JpaRepository<table, String> {

}
