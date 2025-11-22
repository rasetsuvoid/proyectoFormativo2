package com.example.proyectoFormativo.Controller;

import com.example.proyectoFormativo.Dto.DatoEstadisticoDto;
import com.example.proyectoFormativo.Services.ReporteJasperService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ReporteJasperController {

    private final ReporteJasperService reporteJasperService;

    public ReporteJasperController(ReporteJasperService reporteJasperService) {
        this.reporteJasperService = reporteJasperService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/api/reportes/jasper/estadistico")
    public ResponseEntity<Map<String, Object>> getReporteEstadistico() {
        // 1. Datos de ejemplo (normalmente vienen de la BD)
        List<DatoEstadisticoDto> datos = List.of(
                new DatoEstadisticoDto("Enero", 123.45),
                new DatoEstadisticoDto("Febrero", 98.76),
                new DatoEstadisticoDto("Marzo", 150.00)
        );

        // 5. Parámetros del reporte
        Map<String, Object> params = new HashMap<>();
        params.put("TITULO", "Estadísticas de Ventas por Mes");
        params.put("NOMBREMASCOTA", "Firulais");

        byte[] pdf = reporteJasperService.generarReporteEstadisticoPdf(datos, "resporte/reporte_estadistico.jrxml", params);

        // Convertir a Base64
        String base64 = Base64.getEncoder().encodeToString(pdf);

        // Crear JSON de respuesta
        Map<String, Object> body = new HashMap<>();
        body.put("nombre", "reporte-estadistico-jasper.pdf");
        body.put("base64", base64);

        return ResponseEntity.ok(body);
    }
}