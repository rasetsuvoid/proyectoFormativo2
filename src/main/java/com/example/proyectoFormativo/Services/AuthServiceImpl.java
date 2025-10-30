package com.example.proyectoFormativo.Services;

import com.example.proyectoFormativo.Dto.AuthorDto;
import com.example.proyectoFormativo.Dto.JwtResponse;
import com.example.proyectoFormativo.Dto.LoginRequest;
import com.example.proyectoFormativo.Dto.RegisterRequest;
import com.example.proyectoFormativo.Dto.Response.ApiResponse;
import com.example.proyectoFormativo.Interface.IAuthService;
import com.example.proyectoFormativo.Model.Role;
import com.example.proyectoFormativo.Model.User;
import com.example.proyectoFormativo.Repository.IRoleRepository;
import com.example.proyectoFormativo.Repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class AuthServiceImpl implements IAuthService {

    private final AuthenticationManager authManager;
    private final UserDetailsService uds;        // o tu UserService
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final IUserRepository userRepository; // para persistir registro
    private final IRoleRepository roleRepository;

    public AuthServiceImpl(AuthenticationManager authManager, UserDetailsService uds, JwtService jwtService, PasswordEncoder passwordEncoder, IUserRepository userRepository, IRoleRepository roleRepository) {
        this.authManager = authManager;
        this.uds = uds;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }


    @Override
    @Transactional
    public ApiResponse<String> register(RegisterRequest req) {
        ApiResponse<String> response = new ApiResponse<>();
        try{
            // reglas de negocio: existencia, encoder, rol por defecto, persistir, etc.
            if (userRepository.existsByUsername(req.username())) {
                response.setHttpStatusCode(HttpStatus.CONFLICT.value());
                response.setMessage("El usuario ya existe");
                response.setData(null);
                return response;
            }

            User user = new User();
            user.setUsername(req.username());
            user.setPassword(passwordEncoder.encode(req.password()));
            user.setEmail(req.email());
            user.setActive(true);
            // asignar roles...
            Role roleBibliotecario = roleRepository.findByName("BIBLIOTECARIO")
                    .orElseThrow(() -> new RuntimeException("Rol base no existe: ROLE_USER"));

            Set<Role> roles = new HashSet<>();
            roles.add(roleBibliotecario);
            user.setRoles(roles);

            userRepository.save(user);

            response.setHttpStatusCode(HttpStatus.CREATED.value());
            response.setMessage("Registro exitoso");
            response.setData("Usuario creado");
            return response;
        }catch (Exception ex){
            response.setHttpStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setMessage("Ocurrió un error al registrar el usuario: " + ex.getMessage());
        }
        return response;
    }

    @Override
    public ApiResponse<JwtResponse> login(LoginRequest req) {
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.username(), req.password())
        );

        var user = uds.loadUserByUsername(req.username());
        var token = jwtService.generateToken(user.getUsername(), user.getAuthorities());

        var payload = new JwtResponse(token, "Bearer", jwtService.getExpiresInSeconds());

        ApiResponse<JwtResponse> response = new ApiResponse<>();
        response.setHttpStatusCode(HttpStatus.OK.value());
        response.setMessage("Inicio de sesión exitoso");
        response.setData(payload);
        return response;
    }
}