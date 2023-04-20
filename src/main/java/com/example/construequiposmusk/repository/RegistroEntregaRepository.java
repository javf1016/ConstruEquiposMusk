package com.example.construequiposmusk.repository;

import com.example.construequiposmusk.model.RegistroEntrega;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistroEntregaRepository extends JpaRepository<RegistroEntrega, Long> {
}