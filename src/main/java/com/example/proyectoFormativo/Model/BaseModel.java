package com.example.proyectoFormativo.Model;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @CreatedDate
    @Column(name = "created_date", nullable = false, updatable = false)
    private LocalDateTime CreatedDate;

    @LastModifiedDate
    @Column(name = "updated_date")
    private LocalDateTime UpdatedDate;

    @Column(name = "active", nullable = false)
    private Boolean Active = true;

    @Column(name = "is_deleted", nullable = false)
    private Boolean IsDeleted = false;

    // --- Hooks para fallback si el auditing no está activo ---
    @PrePersist
    public void prePersist() {
        if (CreatedDate == null) {
            CreatedDate = LocalDateTime.now();
        }
        if (Active == null) {
            Active = true;
        }
        if (IsDeleted == null) {
            IsDeleted = false;
        }
    }

    @PreUpdate
    public void preUpdate() {
        UpdatedDate = LocalDateTime.now();
    }

    // --- Getters y Setters ---
    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public LocalDateTime getCreatedDate() {
        return CreatedDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        CreatedDate = createdDate;
    }

    public LocalDateTime getUpdatedDate() {
        return UpdatedDate;
    }

    public void setUpdatedDate(LocalDateTime updatedDate) {
        UpdatedDate = updatedDate;
    }

    public Boolean getActive() {
        return Active;
    }

    public void setActive(Boolean active) {
        Active = active;
    }

    public Boolean getDeleted() {
        return IsDeleted;
    }

    public void setDeleted(Boolean deleted) {
        IsDeleted = deleted;
    }
}