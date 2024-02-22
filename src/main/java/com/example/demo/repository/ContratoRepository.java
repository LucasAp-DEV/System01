package com.example.demo.repository;

import com.example.demo.domain.contrato.Contrato;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContratoRepository extends JpaRepository<Contrato, Long> {
}
