package com.example.proyectoFormativo.Interface;

import com.example.proyectoFormativo.Dto.AuthorDto;
import com.example.proyectoFormativo.Model.Author;

import java.util.List;
import java.util.Optional;

public interface IAuthorServices {
    List<AuthorDto> findAll();
    Optional<Author> findById(Long id);
    AuthorDto create(AuthorDto dto);
    Author update(Long id, Author author);
    void delete(Long id);
}
