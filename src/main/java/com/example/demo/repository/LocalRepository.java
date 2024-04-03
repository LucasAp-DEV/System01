package com.example.demo.repository;

import com.example.demo.domain.local.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LocalRepository extends JpaRepository<Local, Long> {
}
