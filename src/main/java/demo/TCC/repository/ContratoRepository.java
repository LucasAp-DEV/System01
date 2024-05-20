package demo.TCC.repository;

import demo.TCC.domain.contrato.Contrato;
import demo.TCC.domain.local.Local;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ContratoRepository extends JpaRepository<Contrato, Long> {

    List<Contrato> findByLocatario_IdOrLocador_Id(Long locador, Long locatario);

    Optional<Contrato> findByDataAndLocal(LocalDate data, Local local);

}

