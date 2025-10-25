package com.example.projectoFormativo.Repository;

import com.example.projectoFormativo.Model.Copy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICopyRepository  extends JpaRepository<Copy, Long> {
}
