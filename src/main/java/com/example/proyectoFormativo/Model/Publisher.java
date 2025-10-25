package com.example.proyectoFormativo.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Publisher extends BaseModel {
    @Column(nullable = false) private String name;
    private String country;
    private String website;

    @OneToMany(mappedBy = "publisher")
    private List<Book> books = new ArrayList<>();
}
