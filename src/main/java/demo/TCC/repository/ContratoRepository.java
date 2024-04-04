package demo.TCC.repository;

import demo.TCC.domain.contrato.Contrato;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContratoRepository extends JpaRepository<Contrato, Long> {
}
