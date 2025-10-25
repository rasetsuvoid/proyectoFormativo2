package com.example.projectoFormativo.Controller;

import com.example.projectoFormativo.Dto.AuthorDto;
import com.example.projectoFormativo.Interface.IAuthorServices;
import com.example.projectoFormativo.Model.Author;
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
