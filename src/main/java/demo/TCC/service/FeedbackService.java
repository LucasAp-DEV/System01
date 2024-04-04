package demo.TCC.service;

import demo.TCC.domain.feedback.Feedback;
import demo.TCC.repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackService {

    @Autowired
    private FeedbackRepository repository;

    //Retorna ua lista de Feedbacks
    public List<Feedback> returnAll() {
        return repository.findAll();
    }

    //Salvando o Feedback
    public Feedback saveFeedback (Feedback feedback) {
        return repository.save(feedback);
    }

    //Buscando Feedback por ID
    public Feedback returnId(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Feedback não encontrado"));
    }

    //Deletando Feedback
    public void deleteById(Long id) {
        var feedback = returnId(id);
        repository.delete(feedback);
    }
}
