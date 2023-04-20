package com.example.construequiposmusk.repository;

import com.example.construequiposmusk.model.Contrato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContratoRepository extends JpaRepository<Contrato, Long> {
    // Métodos personalizados si es necesario
}