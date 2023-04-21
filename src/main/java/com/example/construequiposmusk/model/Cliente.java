package com.example.construequiposmusk.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Entity
@Data
@Table(name = "clientes")
@ToString
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cliente_id")
    private Long clienteId;

    private String nombre;
    private String direccion;
    // Relación uno a muchos con Contrato
    @OneToMany(mappedBy = "cliente", fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Contrato> contratos;
}