package com.example.proyectoFormativo.Seeds;


import com.example.proyectoFormativo.Model.Role;
import com.example.proyectoFormativo.Repository.IRoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Component
public class RoleSeeder implements CommandLineRunner {

    private final IRoleRepository roleRepository;

    public RoleSeeder(IRoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    @Transactional
    public void run(String... args) {
        List<String> roles = Arrays.asList("ADMIN", "BIBLIOTECARIO", "LECTOR");

        for (String roleName : roles) {
            roleRepository.findByName(roleName).ifPresentOrElse(
                    existing -> System.out.println("Rol existente: " + existing.getName()),
                    () -> {
                        Role role = new Role();
                        role.setName(roleName);
                        roleRepository.save(role);
                        System.out.println("Rol creado: " + roleName);
                    }
            );
        }
    }
}
