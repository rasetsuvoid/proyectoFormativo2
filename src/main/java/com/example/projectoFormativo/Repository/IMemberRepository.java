package com.example.projectoFormativo.Repository;

import com.example.projectoFormativo.Model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMemberRepository  extends JpaRepository<Member, Long> {
}
