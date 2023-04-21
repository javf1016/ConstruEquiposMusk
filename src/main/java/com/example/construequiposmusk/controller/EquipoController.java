package com.example.construequiposmusk.controller;

import com.example.construequiposmusk.exception.ContratoNoEncontradoException;
import com.example.construequiposmusk.exception.EquipoNoDisponibleException;
import com.example.construequiposmusk.exception.EquipoNoEncontradoException;
import com.example.construequiposmusk.model.request.AsignacionRequest;
import com.example.construequiposmusk.model.Equipo;
import com.example.construequiposmusk.model.RegistroEntrega;
import com.example.construequiposmusk.model.request.RegistroEntregaRequest;
import com.example.construequiposmusk.model.request.ReparacionRequest;
import com.example.construequiposmusk.service.EquipoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/equipos")
public class EquipoController {

    @Autowired
    private EquipoService equipoService;

    // Endpoint para crear un equipo
    @PostMapping
    public ResponseEntity<Equipo> crearEquipo(@RequestBody Equipo equipo) {
        Equipo equipoCreado = equipoService.crearEquipo(equipo);
        return ResponseEntity.ok(equipoCreado);
    }

    // Endpoint para obtener un equipo por ID
    @GetMapping("/{equipoId}")
    public ResponseEntity<?> obtenerEquipo(@PathVariable Long equipoId) {
        try {
            Equipo equipo = equipoService.obtenerEquipo(equipoId);
            return ResponseEntity.ok(equipo);
        }catch (EquipoNoEncontradoException ene){
            return ResponseEntity.badRequest().body(ene.getMessage());
        }
    }

    // Endpoint para obtener todos los equipos disponibles
    @GetMapping("/disponibles")
    public ResponseEntity<?> obtenerEquiposDisponibles() {
        List<Equipo> equiposDisponibles = equipoService.obtenerEquiposDisponibles();
        return ResponseEntity.ok(equiposDisponibles);
    }

    // Endpoint para obtener todos los equipos en mantenimiento
    @GetMapping("/mantenimiento")
    public ResponseEntity<?> obtenerEquiposEnMantenimiento() {
        List<Equipo> equiposEnMantenimiento = equipoService.obtenerEquiposEnMantenimiento();
        return ResponseEntity.ok(equiposEnMantenimiento);
    }

    // Endpoint para asignar un equipo a un contrato
    @PostMapping("/{equipoId}/asignar")
    public ResponseEntity<?> asignarEquipo(@PathVariable Long equipoId, @RequestBody AsignacionRequest asignacionRequest) throws EquipoNoDisponibleException {
        LocalDate fechaInicio = asignacionRequest.getFechaInicio();
        LocalDate fechaFin = asignacionRequest.getFechaFin();
        boolean incluyeTransporte = asignacionRequest.isIncluyeTransporte();
        Long contratoId = asignacionRequest.getContratoId();
        try{
            Equipo equipoAsignado = equipoService.asignarEquipo(equipoId, contratoId, fechaInicio, fechaFin, incluyeTransporte);
            return ResponseEntity.ok(equipoAsignado);
        }catch (EquipoNoDisponibleException ene){
            return ResponseEntity.badRequest().body(ene.getMessage());
        }catch (ContratoNoEncontradoException cne){
            return ResponseEntity.badRequest().body(cne.getMessage());
        }
    }

    // Endpoint para registrar la entrega de un equipo
    @PostMapping("/{equipoId}/entrega")
    public ResponseEntity<?> registrarEntrega(@PathVariable Long equipoId, @RequestBody RegistroEntregaRequest registroEntregaRequest) throws EquipoNoEncontradoException {
        boolean enBuenEstado = registroEntregaRequest.isEnBuenEstado();
        try {
            RegistroEntrega registroEntrega = equipoService.registrarEntrega(equipoId, enBuenEstado);
            return ResponseEntity.ok(registroEntrega);
        }catch (EquipoNoEncontradoException ene){
            return ResponseEntity.badRequest().body(ene.getMessage());
        }
    }

    // Endpoint para registrar la reparación de un equipo
    @PostMapping("/{equipoId}/reparacion")
    public ResponseEntity<?> registrarReparacion(@PathVariable Long equipoId, @RequestBody ReparacionRequest reparacionRequest) throws EquipoNoEncontradoException {
        double costoReparacion = reparacionRequest.getCostoReparacion();
        try {
            equipoService.registrarReparacion(equipoId, costoReparacion);
            return ResponseEntity.ok().build();
        }catch (EquipoNoEncontradoException ene){
            return ResponseEntity.badRequest().body(ene.getMessage());
        }
    }
}
