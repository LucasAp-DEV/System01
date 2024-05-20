package demo.TCC.repository;

import demo.TCC.domain.local.Local;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LocalRepository extends JpaRepository<Local, Long> {
    List<Local> findByLocatario_Id(Long locatario);
}
