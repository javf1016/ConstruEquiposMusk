package com.example.construequiposmusk.controller;

import com.example.construequiposmusk.exception.ClienteNoEncontradoException;
import com.example.construequiposmusk.model.Contrato;
import com.example.construequiposmusk.service.ContratoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contratos")
public class ContratoController {

    @Autowired
    private ContratoService contratoService;

    // Endpoint para crear un contrato
    @PostMapping
    public ResponseEntity<?> crearContrato(@RequestBody Contrato contrato) {
        try {
            Contrato nuevoContrato = contratoService.crearContrato(contrato);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoContrato);
        }catch (ClienteNoEncontradoException cne){
            return ResponseEntity.badRequest().body(cne.getMessage());
        }
    }

    // Endpoint para obtener un contrato por ID
    @GetMapping("/{contratoId}")
    public ResponseEntity<?> obtenerContrato(@PathVariable Long contratoId) {
        try{
            Contrato contrato = contratoService.obtenerContrato(contratoId);
                return ResponseEntity.ok().body(contrato);
        }catch (ClienteNoEncontradoException cne){
            return ResponseEntity.badRequest().body(cne.getMessage());
        }
    }
    // Endpoint para obtener todos los contratos de un cliente
    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<?> obtenerContratosPorCliente(@PathVariable Long clienteId) {
        try {
            List<Contrato> contratos = contratoService.obtenerContratosPorCliente(clienteId);
            return ResponseEntity.ok().body(contratos);
        }catch (ClienteNoEncontradoException cne){
            return ResponseEntity.badRequest().body(cne.getMessage());
        }
    }
}