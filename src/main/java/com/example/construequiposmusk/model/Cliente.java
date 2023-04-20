package com.example.construequiposmusk.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "clientes")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cliente_id")
    private Long clienteId;

    private String nombre;
    private String direccion;
    // Relación uno a muchos con Contrato
    @OneToMany(mappedBy = "clienteId", fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Contrato> contratos;
}