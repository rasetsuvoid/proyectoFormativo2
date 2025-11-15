package com.example.proyectoFormativo.Repository;


import com.example.proyectoFormativo.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IUserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    @Query("""
           SELECT u 
           FROM User u 
           JOIN u.roles r 
           WHERE r.name = :roleName
           """)
    List<User> findAllByRoleName(@Param("roleName") String roleName);
}