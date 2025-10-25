package com.example.proyectoFormativo.Model;

import jakarta.persistence.*;

@Entity
public class Copy extends BaseModel {
    @ManyToOne
    @JoinColumn(name="book_id", nullable=false)
    private Book book;

    @Column(nullable = false, unique = true)
    private String barcode;

    private String location;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status = Status.AVAILABLE;

    public enum Status { AVAILABLE, LOANED, LOST, MAINTENANCE }
}