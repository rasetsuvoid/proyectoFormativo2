package com.example.proyectoFormativo.Controller;

import com.example.proyectoFormativo.Dto.NotificationRequest;
import com.example.proyectoFormativo.Dto.Response.ApiResponse;
import com.example.proyectoFormativo.Interface.INotificationService;
import com.example.proyectoFormativo.Services.NotificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    private final INotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/email/clients")
    public ResponseEntity<ApiResponse<String>> sendEmailToClients(@Validated @RequestBody NotificationRequest request) {
        ApiResponse<String> response = notificationService.sendNotificationToAllClients(request);

        return ResponseEntity.status(response.getHttpStatusCode()).body(response);
    }
}