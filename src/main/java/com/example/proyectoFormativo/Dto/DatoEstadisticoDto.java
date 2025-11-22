package com.example.proyectoFormativo.Dto;

public class DatoEstadisticoDto {
    private String label;
    private Double valor;

    public DatoEstadisticoDto(String label, Double valor) {
        this.label = label;
        this.valor = valor;
    }

    public String getLabel() { return label; }
    public Double getValor() { return valor; }
}
