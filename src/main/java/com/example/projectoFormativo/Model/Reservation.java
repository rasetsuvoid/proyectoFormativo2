package com.example.projectoFormativo.Model;

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
}