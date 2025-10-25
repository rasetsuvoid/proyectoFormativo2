package com.example.proyectoFormativo.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Category extends BaseModel {
    @Column(nullable = false, unique = true) private String name;

    @ManyToMany(mappedBy = "categories")
    private Set<Book> books = new HashSet<>();
}