package com.example.construequiposmusk.service;

import com.example.construequiposmusk.exception.EquipoNoDisponibleException;
import com.example.construequiposmusk.exception.EquipoNoEncontradoException;
import com.example.construequiposmusk.model.Equipo;
import com.example.construequiposmusk.model.RegistroEntrega;

import java.time.LocalDate;
import java.util.List;

public interface EquipoService {
    Equipo crearEquipo(Equipo equipo);
    Equipo obtenerEquipo(Long equipoId);
    List<Equipo> obtenerEquiposDisponibles();
    List<Equipo> obtenerEquiposEnMantenimiento();
    Equipo asignarEquipo(Long equipoId, Long contratoId, LocalDate fechaInicio, LocalDate fechaFin, boolean incluyeTransporte) throws EquipoNoDisponibleException;
    RegistroEntrega registrarEntrega(Long equipoId, boolean enBuenEstado) throws EquipoNoEncontradoException;
    void registrarReparacion(Long equipoId, double costoReparacion) throws EquipoNoEncontradoException;
}