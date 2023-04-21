package com.example.construequiposmusk.model.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AsignacionRequest {
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private boolean incluyeTransporte;
    private Long contratoId;
}