package com.example.projectoFormativo.Services;

import com.example.projectoFormativo.Dto.AuthorDto;
import com.example.projectoFormativo.Interface.IAuthorServices;
import com.example.projectoFormativo.Model.Author;
import com.example.projectoFormativo.Repository.IAuthorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServices implements IAuthorServices {

    private final IAuthorRepository repo;

    public AuthorServices(IAuthorRepository repository){
        this.repo = repository;
    }

    @Override
    public List<AuthorDto> findAll() {
        var response = repo.findAll();

        List<AuthorDto> authorsDto = response.stream()
                .map(author -> {
                    AuthorDto dto = new AuthorDto();
                    dto.setId(author.getId());
                    dto.setFirstName(author.getFirstName());
                    dto.setLastName(author.getLastName());
                    dto.setBirthDate(author.getBirthDate());
                    dto.setNationality(author.getNationality());
                    return dto;
                })
                .toList();

        return authorsDto;
    }

    @Override
    public Optional<Author> findById(Long id) {
        return repo.findById(id);
    }

    // ---------- CREATE ----------
    @Transactional
    @Override
    public AuthorDto create(AuthorDto dto) {
        Author entity = toEntity(dto);
        // aseguramos que no venga id para crear
        entity.setId(null);
        Author saved = repo.save(entity);
        return toDto(saved);
    }


    @Override
    public Author update(Long id, Author author) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    private Author toEntity(AuthorDto dto) {
        if (dto == null) return null;
        Author a = new Author();
        a.setId(dto.getId());
        a.setFirstName(dto.getFirstName());
        a.setLastName(dto.getLastName());
        a.setNationality(dto.getNationality());
        a.setBirthDate(dto.getBirthDate());
        return a;
    }

    private AuthorDto toDto(Author a) {
        if (a == null) return null;
        AuthorDto dto = new AuthorDto();
        dto.setId(a.getId());
        dto.setFirstName(a.getFirstName());
        dto.setLastName(a.getLastName());
        dto.setNationality(a.getNationality());
        return dto;
    }
}
