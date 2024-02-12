package com.example.demo.repository;

import com.example.demo.model.user.servicoUser.ServicoUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServicosUserRepository extends JpaRepository<ServicoUserEntity, Long> {
}
