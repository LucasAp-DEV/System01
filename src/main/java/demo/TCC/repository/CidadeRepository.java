package demo.TCC.repository;

import demo.TCC.domain.cidade.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CidadeRepository extends JpaRepository<Cidade, Long> {

    Cidade findByName(String name);

}
