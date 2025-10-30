package com.example.proyectoFormativo.Interface;

import com.example.proyectoFormativo.Dto.RegisterRequest;
import com.example.proyectoFormativo.Dto.Response.ApiResponse;
import com.example.proyectoFormativo.Model.User;

public interface IUserService {
    ApiResponse<String> register(RegisterRequest req);
}
