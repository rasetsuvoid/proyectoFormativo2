package com.example.proyectoFormativo.Controller;

import com.example.proyectoFormativo.Dto.AuthorDto;
import com.example.proyectoFormativo.Interface.IAuthorServices;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/author")
public class AuthorController {
    private final IAuthorServices services;

    public AuthorController(IAuthorServices services) {
        this.services = services;
    }

    @GetMapping
    public List<AuthorDto> ListarAutores(){
        return services.findAll();
    }

    @PostMapping
    public AuthorDto CrearAuthor(@RequestBody AuthorDto body){
        return services.create(body);
    }

}
