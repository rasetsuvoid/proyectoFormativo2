package com.example.projectoFormativo.Repository;

import com.example.projectoFormativo.Model.Loan;
import com.example.projectoFormativo.Model.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPublisherRepository  extends JpaRepository<Publisher, Long> {
}
