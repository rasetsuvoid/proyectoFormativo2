package com.example.proyectoFormativo.Repository;

import com.example.proyectoFormativo.Model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IAuthorRepository extends JpaRepository<Author, Long> {
    @Query("SELECT a FROM Author a WHERE a.Active = true AND a.IsDeleted = false")
    List<Author> findByActiveTrueAndIsDeletedFalse();
}
