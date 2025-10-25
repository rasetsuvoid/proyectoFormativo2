package com.example.proyectoFormativo.Repository;

import com.example.proyectoFormativo.Model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IReservationRepository  extends JpaRepository<Reservation, Long> {
}
