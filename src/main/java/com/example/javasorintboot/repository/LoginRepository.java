package com.example.javasorintboot.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.javasorintboot.entity.LoginEntity;

@Repository
public interface LoginRepository extends JpaRepository<LoginEntity, String> {
    Optional<LoginEntity> findByEmailAndPassword(String email, String password);

}
