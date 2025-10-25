package com.example.projectoFormativo.Interface;

import com.example.projectoFormativo.Dto.AuthorDto;
import com.example.projectoFormativo.Model.Author;

import java.util.List;
import java.util.Optional;

public interface IAuthorServices {
    List<AuthorDto> findAll();
    Optional<Author> findById(Long id);
    AuthorDto create(AuthorDto dto);
    Author update(Long id, Author author);
    void delete(Long id);
}
