package com.example.demo.repository;

import com.example.demo.domain.cidade.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CidadeRepository extends JpaRepository<Cidade, Long> {

    Cidade findByName(String name);

}
