package com.example.proyectoFormativo.Repository;

import com.example.proyectoFormativo.Model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMemberRepository  extends JpaRepository<Member, Long> {
}
