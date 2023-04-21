package com.example.construequiposmusk.repository;

import com.example.construequiposmusk.model.Cliente;
import com.example.construequiposmusk.model.Contrato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContratoRepository extends JpaRepository<Contrato, Long> {
    List<Contrato> findByCliente(Cliente cliente);
}