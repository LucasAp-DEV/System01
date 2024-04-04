package demo.TCC.repository;

import demo.TCC.domain.local.Local;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocalRepository extends JpaRepository<Local, Long> {
}
