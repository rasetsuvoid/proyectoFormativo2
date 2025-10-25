package com.example.proyectoFormativo.Model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Book extends BaseModel {
    @Column(nullable = false, unique = true) private String isbn;
    @Column(nullable = false) private String title;
    @Column(length = 4000) private String description;
    private Integer publishYear;
    private Integer pages;

    @ManyToOne
    @JoinColumn(name="publisher_id")
    private Publisher publisher;

    @ManyToMany
    @JoinTable(name="book_author",
            joinColumns=@JoinColumn(name="book_id"),
            inverseJoinColumns=@JoinColumn(name="author_id"))
    private Set<Author> authors = new HashSet<>();

    @ManyToMany
    @JoinTable(name="book_category",
            joinColumns=@JoinColumn(name="book_id"),
            inverseJoinColumns=@JoinColumn(name="category_id"))
    private Set<Category> categories = new HashSet<>();

    @OneToMany(mappedBy = "book")
    private List<Copy> copies = new ArrayList<>();
}