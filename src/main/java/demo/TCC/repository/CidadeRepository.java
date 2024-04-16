package demo.TCC.repository;

import demo.TCC.domain.cidade.Cidade;
import demo.TCC.domain.contrato.Contrato;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CidadeRepository extends JpaRepository<Cidade, Long> {

    Cidade findByName(String name);

}
