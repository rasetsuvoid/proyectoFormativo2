package com.example.proyectoFormativo.Services;

import com.example.proyectoFormativo.Dto.AuthorDto;
import com.example.proyectoFormativo.Dto.NotificationRequest;
import com.example.proyectoFormativo.Dto.Response.ApiResponse;
import com.example.proyectoFormativo.Interface.INotificationService;
import com.example.proyectoFormativo.Model.User;
import com.example.proyectoFormativo.Repository.IUserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class NotificationService implements INotificationService {

    private final IUserRepository userRepository;
    private final EmailService emailService;

    public NotificationService(IUserRepository userRepository,
                               EmailService emailService) {
        this.userRepository = userRepository;
        this.emailService = emailService;
    }

    public ApiResponse<String> sendNotificationToAllClients(NotificationRequest request) {
        ApiResponse<String> response = new ApiResponse<>();

        try {
            List<User> clients = userRepository.findAllByRoleName("CLIENTE");

            for (User client : clients) {
                String email = client.getEmail();
                if (email != null && !email.isBlank()) {
                    emailService.sendHtmlEmail(email,
                            request.getSubject(),
                            request.getHtmlBody());
                }
            }

            int clientSend = clients.size();
            response.setHttpStatusCode(200);
            response.setMessage("Se han enviado notificaciones a " + clientSend + " clientes.");
            response.setData("Se han enviado notificaciones a " + clientSend + " clientes.");

        }catch (Exception ex){
            response.setHttpStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setMessage("Ocurrió un error al obtener la lista de autores: " + ex.getMessage());
            response.setData("");
            response.setTotalRecords(0);
        }

        return response;
    }
}