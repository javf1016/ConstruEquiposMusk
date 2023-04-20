package com.example.construequiposmusk.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "registrosentrega")
public class RegistroEntrega {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reistro_entrega_id")
    private Long registroEntregaId;

    @ManyToOne
    private Contrato contrato;

    @ManyToOne
    private Equipo equipo;

    private boolean incluidoEnContrato;
    private boolean incluyeTransporte;
}