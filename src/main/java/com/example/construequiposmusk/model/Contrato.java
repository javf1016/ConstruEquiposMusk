package com.example.construequiposmusk.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Table(name = "contratos")
public class Contrato {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contrato_id")
    private Long contratoId;

    @ManyToOne
    private Cliente cliente;

    @ManyToMany
    private List<Equipo> equipos;

    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private boolean contratoActivo;
    private boolean incluyeTransporte;

}