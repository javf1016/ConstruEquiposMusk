package com.example.construequiposmusk.service;

import com.example.construequiposmusk.model.Contrato;

import java.util.List;

public interface ContratoService {
    Contrato crearContrato(Contrato contrato);
    Contrato obtenerContrato(Long contratoId);
    List<Contrato> obtenerContratosPorCliente(Long clienteId);
}