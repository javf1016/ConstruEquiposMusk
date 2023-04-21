package com.example.construequiposmusk.repository;

import com.example.construequiposmusk.model.Equipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EquipoRepository extends JpaRepository<Equipo, Long> {
    List<Equipo> findByDisponibleTrue();
    List<Equipo> findByDisponibleFalse();
}