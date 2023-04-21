package com.example.construequiposmusk.service.impl;

import com.example.construequiposmusk.exception.ClienteNoEncontradoException;
import com.example.construequiposmusk.exception.ContratoNoEncontradoException;
import com.example.construequiposmusk.model.Cliente;
import com.example.construequiposmusk.model.Contrato;
import com.example.construequiposmusk.repository.ClienteRepository;
import com.example.construequiposmusk.repository.ContratoRepository;
import com.example.construequiposmusk.repository.EquipoRepository;
import com.example.construequiposmusk.service.ContratoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ContratoServiceImpl implements ContratoService {

    @Autowired
    private ContratoRepository contratoRepository;

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private EquipoRepository equipoRepository;

    @Override
    public Contrato crearContrato(Contrato contrato) {
        // Verificar que el cliente asociado al contrato exista en la base de datos
        Long clienteId = contrato.getCliente().getClienteId();
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new ClienteNoEncontradoException("No se encontró el cliente con el ID " + clienteId));
        // Establecer la fecha de creación como la fecha actual
        contrato.setFechaInicio(LocalDate.now());
        // Asignar el cliente al contrato
        contrato.setCliente(cliente);
        // Guardar el contrato en la base de datos mediante el repositorio
        return contratoRepository.save(contrato);
    }

    @Override
    public Contrato obtenerContrato(Long contratoId) {
        // lógica para obtener un contrato por su identificador
        return contratoRepository.findById(contratoId)
                .orElseThrow(() -> new ContratoNoEncontradoException("No se encontró el contrato con el ID " + contratoId));
    }

    @Override
    public List<Contrato> obtenerContratosPorCliente(Long clienteId) {
        // Verificar que el cliente exista en la base de datos
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new ClienteNoEncontradoException("No se encontró el cliente con el ID " + clienteId));
        // lógica para obtener todos los contratos asociados a un cliente
        // por su identificador, utilizando el repositorio de Contrato
        return contratoRepository.findByCliente(cliente);
    }
}