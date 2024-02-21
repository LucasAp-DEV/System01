package com.example.demo.repository;

import com.example.demo.domain.service.Service;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<Service, Long> {
}
