package com.example.projectoFormativo.Repository;

import com.example.projectoFormativo.Model.Loan;
import com.example.projectoFormativo.Model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IReservationRepository  extends JpaRepository<Reservation, Long> {
}
