package com.example.ecommerceapp.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String username;

    @Column(unique = true, nullable = false)
    String email;

    LocalDateTime createdAt;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL , orphanRemoval = true)
    private List<Product> products = new ArrayList<>();

}
