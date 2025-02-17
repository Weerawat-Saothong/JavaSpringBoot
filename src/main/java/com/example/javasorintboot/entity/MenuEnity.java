package com.example.javasorintboot.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@Table(name = "menu")
@EqualsAndHashCode(callSuper = true)
public class MenuEnity  extends BaseEnity{
    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "price", nullable = false, length = 10)
    private int price;

    @Column(name = "type", nullable = false, length = 10)
    private String type;


    @Column(name = "files", nullable = false, columnDefinition = "bytea")
    private byte[] files;

    @Transient
    private String base64Image;
}
