package com.example.proyectoFormativo.Controller;

import com.example.proyectoFormativo.Dto.AuthorDto;
import com.example.proyectoFormativo.Dto.Response.ApiResponse;
import com.example.proyectoFormativo.Interface.IAuthorServices;
import com.example.proyectoFormativo.Model.Author;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<ApiResponse<List<AuthorDto>>> listarAutores() {
        ApiResponse<List<AuthorDto>> response = services.findAll();
        return ResponseEntity.status(response.getHttpStatusCode()).body(response);
    }


    @PostMapping("/create")
    public ResponseEntity<ApiResponse<AuthorDto>> create(@Valid @RequestBody AuthorDto dto) {
        ApiResponse<AuthorDto> response = services.create(dto);
        return ResponseEntity.status(response.getHttpStatusCode()).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<AuthorDto>> obtenerAutorPorId(@PathVariable Long id) {
        ApiResponse<AuthorDto> response = services.findById(id);
        return ResponseEntity.status(response.getHttpStatusCode()).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<AuthorDto>> actualizarAutor(
            @PathVariable Long id,
            @RequestBody AuthorDto author) {

        ApiResponse<AuthorDto> response = services.update(id, author);
        return ResponseEntity.status(response.getHttpStatusCode()).body(response);
    }



}
