package com.example.construequiposmusk.service.impl;

import com.example.construequiposmusk.exception.ContratoNoEncontradoException;
import com.example.construequiposmusk.exception.EquipoNoDisponibleException;
import com.example.construequiposmusk.exception.EquipoNoEncontradoException;
import com.example.construequiposmusk.model.Contrato;
import com.example.construequiposmusk.model.Equipo;
import com.example.construequiposmusk.model.RegistroEntrega;
import com.example.construequiposmusk.repository.ContratoRepository;
import com.example.construequiposmusk.repository.EquipoRepository;
import com.example.construequiposmusk.repository.RegistroEntregaRepository;
import com.example.construequiposmusk.service.EquipoService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class EquipoServiceImpl implements EquipoService {

    private final EquipoRepository equipoRepository;
    private final ContratoRepository contratoRepository;
    private final RegistroEntregaRepository registroEntregaRepository;

    public EquipoServiceImpl(EquipoRepository equipoRepository, ContratoRepository contratoRepository, RegistroEntregaRepository registroEntregaRepository) {
        this.equipoRepository = equipoRepository;
        this.contratoRepository = contratoRepository;
        this.registroEntregaRepository = registroEntregaRepository;
    }

    @Override
    public Equipo crearEquipo(Equipo equipo) {
        return equipoRepository.save(equipo);
    }

    @Override
    public Equipo obtenerEquipo(Long equipoId) {
        Optional<Equipo> equipoOptional = equipoRepository.findById(equipoId);
        if (equipoOptional.isEmpty()) {
            throw new EquipoNoEncontradoException("No se encontró el equipo con el ID " + equipoId);
        }
        return equipoOptional.get();
    }

    @Override
    public List<Equipo> obtenerEquiposDisponibles() {
        return equipoRepository.findByDisponibleTrue();
    }

    @Override
    public List<Equipo> obtenerEquiposEnMantenimiento() {
        return equipoRepository.findByDisponibleFalse();
    }

    @Override
    public Equipo asignarEquipo(Long equipoId, Long contratoId, LocalDate fechaInicio, LocalDate fechaFin, boolean incluyeTransporte) throws EquipoNoDisponibleException {
        Equipo equipo = obtenerEquipo(equipoId);
        if (!equipo.isDisponible()) {
            throw new EquipoNoDisponibleException("El equipo con el ID " + equipoId + " no está disponible para ser asignado.");
        }
        Contrato contrato = contratoRepository.findById(contratoId).orElseThrow(() -> new ContratoNoEncontradoException("No se encontró el contrato con el ID " + contratoId));
        equipo.setDisponible(false);
        equipo.getContratos().add(contrato);
        return equipoRepository.save(equipo);
    }

    @Override
    public RegistroEntrega registrarEntrega(Long equipoId, boolean enBuenEstado) throws EquipoNoEncontradoException {
        Equipo equipo = obtenerEquipo(equipoId);
        if (equipo.isDisponible()) {
            throw new EquipoNoEncontradoException("El equipo con el ID " + equipoId + " no ha sido asignado.");
        }
        RegistroEntrega registroEntrega = new RegistroEntrega();
        registroEntrega.setEquipo(equipo);
        registroEntrega.setIncluidoEnContrato(false);
        registroEntrega.setIncluyeTransporte(false);
        registroEntrega.setCostoReparacion(null);
        equipo.setDisponible(true);
        equipo.setContratos(null);
        equipoRepository.save(equipo);
        return registroEntregaRepository.save(registroEntrega);
    }

    @Override
    public void registrarReparacion(Long equipoId, double costoReparacion) throws EquipoNoEncontradoException {
        Equipo equipo = obtenerEquipo(equipoId);
        if (equipo == null) {
            throw new EquipoNoEncontradoException("No se encontró el equipo con ID " + equipoId);
        }
        equipo.setEnReparacion(true);
        equipoRepository.save(equipo);
    }
}