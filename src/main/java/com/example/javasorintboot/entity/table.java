package com.example.javasorintboot.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Data
@Entity
@Table(name = "tables")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)

public class table extends BaseEnity {

    @Column(name = "name", nullable = false, length = 100)
    private String name;
}