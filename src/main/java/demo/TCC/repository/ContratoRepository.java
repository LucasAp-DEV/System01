package demo.TCC.repository;

import demo.TCC.domain.contrato.Contrato;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContratoRepository extends JpaRepository<Contrato, Long> {

    List<Contrato> findAllByLocadorId(Long locadorId);
}