package com.example.proyectoFormativo.Interface;

import com.example.proyectoFormativo.Dto.AuthorDto;
import com.example.proyectoFormativo.Dto.Response.ApiResponse;
import com.example.proyectoFormativo.Model.Author;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;

@Validated
public interface IAuthorServices {
    ApiResponse<List<AuthorDto>> findAll();
    ApiResponse<AuthorDto> findById(Long id);
    ApiResponse<AuthorDto> create(AuthorDto dto);
    ApiResponse<AuthorDto> update(Long id, AuthorDto author);
    void delete(Long id);
}
