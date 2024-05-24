package demo.TCC.service;

import demo.TCC.domain.contrato.Contrato;
import demo.TCC.domain.feedback.Feedback;
import demo.TCC.repository.ContratoRepository;
import demo.TCC.repository.FeedbackRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class FeedbackService {

    private final FeedbackRepository repository;

    private final ContratoRepository repositoryContrato;

    public List<Feedback> returnAll() {
        return repository.findAll();
    }

    public void saveFeedback(Feedback feedback) {
        var contrato = returnIdContrato(feedback.getContrato().getId());
        if (contrato != null) {
            repository.save(feedback);
        } else {
            throw new IllegalArgumentException("Contrato Não Encontrato");
        }
    }

    public Feedback returnId(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Feedback não encontrado"));
    }

    public Contrato returnIdContrato(Long id) {
        return repositoryContrato.findById(id).orElseThrow(() -> new RuntimeException("Contrato não Encontrado"));
    }

    public void deleteById(Long id) {
        var feedback = returnId(id);
        repository.delete(feedback);
    }
}
