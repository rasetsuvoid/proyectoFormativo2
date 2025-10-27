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

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}