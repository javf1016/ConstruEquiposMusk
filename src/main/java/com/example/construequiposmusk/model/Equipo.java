package com.example.construequiposmusk.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "equipos")
public class Equipo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "equipo_id")
    private Long equipoId;

    private String nombre;
    private String descripcion;
    private boolean disponible;
    private boolean enReparacion;
    private boolean dadoDeBaja;
    // Relación muchos a muchos con Contrato
    @ManyToMany(mappedBy = "equipos")
    private List<Contrato> contratos;
}