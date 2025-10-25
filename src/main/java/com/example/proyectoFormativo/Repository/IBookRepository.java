package com.example.proyectoFormativo.Repository;

import com.example.proyectoFormativo.Model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBookRepository extends JpaRepository<Book, Long> {
}
