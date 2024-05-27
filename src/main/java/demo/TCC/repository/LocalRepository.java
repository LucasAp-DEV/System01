package demo.TCC.repository;

import demo.TCC.domain.local.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.time.LocalDate;
import java.util.List;

public interface LocalRepository extends JpaRepository<Local, Long> {
    List<Local> findByLocatario_Id(Long locatario);

//    List<Local> findByCidade_Id(Long cidade);
//    @Query("SELECT l FROM local l WHERE l.cidade_id = :cityId AND l.id NOT IN " +
//            "(SELECT c.local.id FROM contrato c WHERE c.data = :data)")
//
//    List<Local> findByCidadeIdAndExcludeContractsOnDate(@Param("cityId") Long cityId, @Param("data") LocalDate data);

}