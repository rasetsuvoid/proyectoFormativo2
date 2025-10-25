package com.example.proyectoFormativo.Repository;

import com.example.proyectoFormativo.Model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAuthorRepository extends JpaRepository<Author, Long> {
}
