package com.example.proyectoFormativo.Repository;

import com.example.proyectoFormativo.Model.Copy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICopyRepository  extends JpaRepository<Copy, Long> {
}
