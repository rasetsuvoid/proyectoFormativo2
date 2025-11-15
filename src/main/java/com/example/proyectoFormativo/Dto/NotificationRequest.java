package com.example.proyectoFormativo.Dto;

import jakarta.validation.constraints.NotBlank;

public class NotificationRequest {

    @NotBlank
    private String subject;

    @NotBlank
    private String htmlBody;

    // Getters y setters

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getHtmlBody() {
        return htmlBody;
    }

    public void setHtmlBody(String htmlBody) {
        this.htmlBody = htmlBody;
    }
}
