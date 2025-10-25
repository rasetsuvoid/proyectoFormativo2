package com.example.projectoFormativo.Repository;

import com.example.projectoFormativo.Model.Author;
import com.example.projectoFormativo.Model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ILoanRepository extends JpaRepository<Loan, Long> {
}
