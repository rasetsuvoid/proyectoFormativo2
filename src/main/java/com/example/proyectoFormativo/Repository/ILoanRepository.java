package com.example.proyectoFormativo.Repository;

import com.example.proyectoFormativo.Model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ILoanRepository extends JpaRepository<Loan, Long> {
}
