package com.example.proyectoFormativo.Repository;

import com.example.proyectoFormativo.Model.Category;
import com.example.proyectoFormativo.Model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByName(String name);
}
