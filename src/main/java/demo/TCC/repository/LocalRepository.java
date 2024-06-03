package demo.TCC.repository;

import demo.TCC.domain.local.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.time.LocalDate;
import java.util.List;

public interface LocalRepository extends JpaRepository<Local, Long> {
    List<Local> findByLocatario_Id(Long locatario);

    @Query("SELECT l FROM local l WHERE NOT EXISTS (SELECT c FROM contrato c WHERE c.local.id = l.id AND c.data = :data)")
    List<Local> findByContratosDataNot(LocalDate data);
}