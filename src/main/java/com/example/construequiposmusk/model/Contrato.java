package com.example.construequiposmusk.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Table(name = "contratos")
@ToString
public class Contrato {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contrato_id")
    private Long contratoId;

    @ManyToOne
    @JoinColumn(name = "cliente_cliente_id") // nombre de la columna de la clave foránea en la tabla contratos
    private Cliente cliente;

    @ManyToMany
    private List<Equipo> equipos;

    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private boolean contratoActivo;
    private boolean incluyeTransporte;
}