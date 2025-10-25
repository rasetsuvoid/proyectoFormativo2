package com.example.projectoFormativo.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Author extends BaseModel {

    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String nationality;

    @ManyToMany(mappedBy = "authors")

    private Set<Book> books = new HashSet<>();


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }
}
