package com.example.proyectoFormativo.Model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Loan extends BaseModel {
    @ManyToOne
    @JoinColumn(name="member_id", nullable=false)
    private Member member;

    @ManyToOne @JoinColumn(name="copy_id", nullable=false)
    private Copy copy;

    @Column(nullable=false) private LocalDateTime loanDate;
    @Column(nullable=false) private LocalDateTime dueDate;
    private LocalDateTime returnDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable=false)
    private Status status = Status.OPEN;

    @Column(nullable=false) private BigDecimal fineAmount = BigDecimal.ZERO;

    public enum Status { OPEN, RETURNED, LATE }
}