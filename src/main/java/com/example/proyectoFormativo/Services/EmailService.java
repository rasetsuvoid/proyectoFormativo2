package com.example.proyectoFormativo.Services;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    /**
     * Envía un correo HTML a un destinatario.
     * @param to        correo destino
     * @param subject   asunto
     * @param htmlBody  cuerpo HTML
     */
    //@Async
    public void sendHtmlEmail(String to, String subject, String htmlBody) {
        try {

            System.out.println("Enviando correo a " + to);
            MimeMessage message = mailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(
                    message,
                    MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    "UTF-8"
            );

            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(htmlBody, true); // true = HTML

            // Si quieres agregar un remitente fijo:
            // helper.setFrom("no-reply@tuapp.com");

            mailSender.send(message);

        } catch (MessagingException e) {
            throw new RuntimeException("Error enviando correo a " + to, e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}