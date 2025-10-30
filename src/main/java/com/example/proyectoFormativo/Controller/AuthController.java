package com.example.proyectoFormativo.Controller;

import com.example.proyectoFormativo.Dto.AuthorDto;
import com.example.proyectoFormativo.Dto.JwtResponse;
import com.example.proyectoFormativo.Dto.LoginRequest;
import com.example.proyectoFormativo.Dto.RegisterRequest;
import com.example.proyectoFormativo.Dto.Response.ApiResponse;
import com.example.proyectoFormativo.Interface.IAuthService;
import com.example.proyectoFormativo.Interface.IUserService;
import com.example.proyectoFormativo.Services.JwtService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final IAuthService authService;

    public AuthController(IAuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<String>> register(@Valid @RequestBody RegisterRequest req) {
        ApiResponse<String> res = authService.register(req);
        return ResponseEntity.status(res.getHttpStatusCode()).body(res);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<JwtResponse>> login(@Valid @RequestBody LoginRequest req) {
        ApiResponse<JwtResponse> res = authService.login(req);
        return ResponseEntity.status(res.getHttpStatusCode()).body(res);
    }
}