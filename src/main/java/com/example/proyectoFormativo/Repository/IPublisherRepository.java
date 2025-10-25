package com.example.proyectoFormativo.Repository;

import com.example.proyectoFormativo.Model.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPublisherRepository  extends JpaRepository<Publisher, Long> {
}
