package com.example.proyectoFormativo.Services;

import com.example.proyectoFormativo.Dto.AuthorDto;
import com.example.proyectoFormativo.Dto.RegisterRequest;
import com.example.proyectoFormativo.Dto.Response.ApiResponse;
import com.example.proyectoFormativo.Interface.IUserService;
import com.example.proyectoFormativo.Model.Role;
import com.example.proyectoFormativo.Model.User;
import com.example.proyectoFormativo.Repository.IRoleRepository;
import com.example.proyectoFormativo.Repository.IUserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService implements IUserService {

    private final IUserRepository userRepo;
    private final IRoleRepository roleRepo;
    private final PasswordEncoder encoder;

    public UserService(IUserRepository userRepo, IRoleRepository roleRepo, PasswordEncoder encoder) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.encoder = encoder;
    }

    @Transactional
    @Override
    public ApiResponse<String> register(RegisterRequest req) {
        ApiResponse<String> response = new ApiResponse<>();

        try{
            if (userRepo.existsByUsername(req.username())) throw new RuntimeException("Username ya existe");
            if (req.email()!=null && userRepo.existsByEmail(req.email())) throw new RuntimeException("Email ya existe");

            Role roleUser = roleRepo.findByName("ROLE_USER")
                    .orElseThrow(() -> new RuntimeException("Rol base no existe: ROLE_USER"));

            User u = new User();
            u.setUsername(req.username());
            u.setPassword(encoder.encode(req.password()));
            u.setEmail(req.email());
            u.getRoles().add(roleUser);
            userRepo.save(u);

            response.setHttpStatusCode(HttpStatus.OK.value());
            response.setMessage("Usuario creado exitosamente");
            response.setData("Usuario creado exitosamente");

        }catch (Exception ex){
            response.setHttpStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setMessage("Ocurrió un error al crear el usuario: " + ex.getMessage());
        }

        return response;
    }
}
