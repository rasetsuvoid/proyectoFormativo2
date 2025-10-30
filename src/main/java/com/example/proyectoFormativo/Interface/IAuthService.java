package com.example.proyectoFormativo.Interface;

import com.example.proyectoFormativo.Dto.JwtResponse;
import com.example.proyectoFormativo.Dto.LoginRequest;
import com.example.proyectoFormativo.Dto.RegisterRequest;
import com.example.proyectoFormativo.Dto.Response.ApiResponse;

public interface IAuthService {
    ApiResponse<String> register(RegisterRequest req);
    ApiResponse<JwtResponse> login(LoginRequest req);
}
