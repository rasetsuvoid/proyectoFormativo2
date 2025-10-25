package com.example.proyectoFormativo.Model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Member extends BaseModel {
    @Column(nullable = false, unique = true) private String docNumber;
    @Column(nullable = false) private String fullName;
    @Column(unique = true) private String email;
    private String phone;
    private String address;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status = Status.ACTIVE;
    public enum Status { ACTIVE, SUSPENDED }

    @OneToMany(mappedBy = "member") private List<Loan> loans = new ArrayList<>();
    @OneToMany(mappedBy = "member") private List<Reservation> reservations = new ArrayList<>();
}
