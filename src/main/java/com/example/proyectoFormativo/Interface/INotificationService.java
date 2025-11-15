package com.example.proyectoFormativo.Interface;

import com.example.proyectoFormativo.Dto.NotificationRequest;
import com.example.proyectoFormativo.Dto.Response.ApiResponse;

public interface INotificationService {
    ApiResponse<String> sendNotificationToAllClients(NotificationRequest request);
}
