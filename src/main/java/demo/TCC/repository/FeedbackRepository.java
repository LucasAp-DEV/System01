package demo.TCC.repository;

import demo.TCC.domain.contrato.Contrato;
import demo.TCC.domain.feedback.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {

}
