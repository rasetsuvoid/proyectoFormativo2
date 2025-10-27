package com.example.proyectoFormativo.Model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Reservation extends BaseModel {
    @ManyToOne
    @JoinColumn(name="member_id", nullable=false)
    private Member member;

    @ManyToOne @JoinColumn(name="book_id", nullable=false)
    private Book book;

    @Column(nullable=false) private LocalDateTime reservedAt;
    @Column(nullable=false) private LocalDateTime expiresAt;

    @Enumerated(EnumType.STRING)
    @Column(nullable=false)
    private Status status = Status.ACTIVE;

    public enum Status { ACTIVE, FULFILLED, CANCELLED }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public LocalDateTime getReservedAt() {
        return reservedAt;
    }

    public void setReservedAt(LocalDateTime reservedAt) {
        this.reservedAt = reservedAt;
    }

    public LocalDateTime getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(LocalDateTime expiresAt) {
        this.expiresAt = expiresAt;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}