package com.example.proyectoFormativo.Services;

import com.example.proyectoFormativo.Dto.AuthorDto;
import com.example.proyectoFormativo.Dto.Response.ApiResponse;
import com.example.proyectoFormativo.Interface.IAuthorServices;
import com.example.proyectoFormativo.Model.Author;
import com.example.proyectoFormativo.Repository.IAuthorRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Validated
public class AuthorServices implements IAuthorServices {

    private final IAuthorRepository repo;
    private final ModelMapper mapper;

    public AuthorServices(IAuthorRepository repository, ModelMapper mapper){
        this.repo = repository;
        this.mapper = mapper;
    }

    @Override
    public ApiResponse<List<AuthorDto>> findAll() {
        ApiResponse<List<AuthorDto>> response = new ApiResponse<>();

        try {
            List<AuthorDto> autores = repo.findByActiveTrueAndIsDeletedFalse()
                    .stream()
                    .map(author -> mapper.map(author, AuthorDto.class))
                    .toList();

            response.setHttpStatusCode(HttpStatus.OK.value());
            response.setMessage(autores.isEmpty()
                    ? "No hay autores registrados."
                    : "Lista de autores obtenida correctamente.");
            response.setData(autores);
            response.setTotalRecords(autores.size());

        } catch (Exception ex) {
            response.setHttpStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setMessage("Ocurrió un error al obtener la lista de autores: " + ex.getMessage());
            response.setData(Collections.emptyList());
            response.setTotalRecords(0);
        }

        return response;
    }

    @Override
    public ApiResponse<AuthorDto> findById(Long id) {
        ApiResponse<AuthorDto> response = new ApiResponse<>();

        try {
            Author author = repo.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Autor no encontrado con ID: " + id));

            AuthorDto dto = mapper.map(author, AuthorDto.class);

            response.setHttpStatusCode(HttpStatus.OK.value());
            response.setMessage("Autor obtenido correctamente");
            response.setData(dto);
        }
        catch (EntityNotFoundException ex) {
            response.setHttpStatusCode(HttpStatus.NOT_FOUND.value());
            response.setMessage(ex.getMessage());
        }
        catch (Exception ex) {
            response.setHttpStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setMessage("Ocurrió un error al obtener el autor: " + ex.getMessage());
        }

        return response;
    }

    // ---------- CREATE ----------
    @Transactional
    @Override
    public ApiResponse<AuthorDto> create(AuthorDto dto) {
        ApiResponse<AuthorDto> response = new ApiResponse<>();

        try {
            Author entity = toEntity(dto);
            entity.setId(null);

            Author saved = repo.save(entity);
            AuthorDto mapped = toDto(saved);

            response.setHttpStatusCode(HttpStatus.CREATED.value());
            response.setMessage("Autor creado exitosamente");
            response.setData(mapped);
        }
        catch (Exception ex) {
            response.setHttpStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setMessage("Error al crear el autor");
            response.setErrors(List.of(ex.getMessage()));
        }

        return response;
    }

    @Transactional
    @Override
    public ApiResponse<AuthorDto> update(Long id, AuthorDto authorRequest) {
        ApiResponse<AuthorDto> response = new ApiResponse<>();

        try {
            // Buscar el autor existente
            Author existingAuthor = repo.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Autor no encontrado con ID: " + id));

            // Actualizar solo los campos permitidos
            existingAuthor.setFirstName(authorRequest.getFirstName());
            existingAuthor.setLastName(authorRequest.getLastName());
            existingAuthor.setNationality(authorRequest.getNationality());
            existingAuthor.setBirthDate(authorRequest.getBirthDate());
            existingAuthor.setActive(authorRequest.isActive());

            // Guardar los cambios
            Author updatedAuthor = repo.save(existingAuthor);

            // Mapear a DTO
            AuthorDto dto = mapper.map(updatedAuthor, AuthorDto.class);

            // Construir la respuesta
            response.setHttpStatusCode(HttpStatus.OK.value());
            response.setMessage("Autor actualizado correctamente");
            response.setData(dto);

        } catch (EntityNotFoundException ex) {
            response.setHttpStatusCode(HttpStatus.NOT_FOUND.value());
            response.setMessage(ex.getMessage());
        } catch (Exception ex) {
            response.setHttpStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setMessage("Ocurrió un error al actualizar el autor: " + ex.getMessage());
        }

        return response;
    }

    @Transactional
    @Override
    public void delete(Long id) {
        try {
            //  Buscar el autor
            Author author = repo.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Autor no encontrado con ID: " + id));

            // Marcar como borrado lógico
            author.setActive(false);
            author.setDeleted(true);
            author.setUpdatedDate(LocalDateTime.now());

            // Guardar cambios
            repo.save(author);

        } catch (EntityNotFoundException ex) {
            System.err.println("⚠️ " + ex.getMessage());
        } catch (Exception ex) {
            System.err.println("Error al eliminar el autor: " + ex.getMessage());
        }
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
