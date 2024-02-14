package com.example.demo.repository;

import com.example.demo.model.user.servicoUser.ServiceUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServicosUserRepository extends JpaRepository<ServiceUser, Long> {
}
