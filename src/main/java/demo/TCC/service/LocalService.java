package demo.TCC.service;

import demo.TCC.domain.contrato.Contrato;
import demo.TCC.domain.feedback.Feedback;
import demo.TCC.domain.feedback.FeedbackDTO;
import demo.TCC.domain.image.Image;
import demo.TCC.domain.local.Local;
import demo.TCC.domain.local.LocalDTO;
import demo.TCC.repository.LocalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class LocalService {

    @Autowired
    private LocalRepository repository;

    public List<LocalDTO> returnAll() {
        List<Local> localList = repository.findAll();
        List<LocalDTO> localDTOS = new ArrayList<>();

        for (Local local : localList) {
            localDTOS.add(converte(local));
        }
        return localDTOS;
    }

    public List<LocalDTO> returUserId(Long id) {
        List<Local> localList = repository.findByLocatario_Id(id);
        List<LocalDTO> localDTOS = new ArrayList<>();

        for (Local local : localList) {
            localDTOS.add(converte(local));
        }
        return localDTOS;
    }

    public Long saveLocal(Local local) {
        Local savedLocal = repository.save(local);
        return savedLocal.getId();
    }

    public LocalDTO findByLocalId(Long id) {
        var local = repository.findById(id).orElseThrow(() -> new RuntimeException("Local não encontrado"));
        return converte(local);
    }

    public Local findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Local não contrado"));
    }

    public void dellLocal(Long id) {
        var dellLocal = findById(id);
        repository.delete(dellLocal);
    }

    public void updateLocal(Long id, Local data) {
        var local = findById(id);
//        validate(data);
        local.setPrice(data.getPrice());
        local.setEndereco(data.getEndereco());
        local.setDescricao(data.getDescricao());
        repository.save(local);
    }

//    private void validate(Local data) {
//        if (Objects.isNull(data.getPrice())) throw new RuntimeException("Status é requirido");
//        if (Objects.isNull(data.getEndereco())) throw new RuntimeException("Status é requirido");
//        if (Objects.isNull(data.getDescricao())) throw new RuntimeException("Status é requirido");
//    }

    public LocalDTO converte(Local local) {
        List<byte[]> imageBytesList = new ArrayList<>();
        for (Image image : local.getImages()) {
            imageBytesList.add(image.getImage());
        }

        List<FeedbackDTO> feedbacks = local.getContratos().stream()
                .flatMap(contrato -> contrato.getFeedbacks().stream())
                .map(this::converteFeedback)
                .collect(Collectors.toList());

        return LocalDTO.builder()
                .id(local.getId())
                .price(local.getPrice())
                .descricao(local.getDescricao())
                .endereco(local.getEndereco())
                .locatarioName(local.getLocatario().getNome())
                .locatarioId(local.getLocatario().getId())
                .locatarioTell(local.getLocatario().getTelefone())
                .cidade(local.getCidade().getName())
                .feedback(feedbacks)
                .images(imageBytesList)
                .build();
    }

    private FeedbackDTO converteFeedback(Feedback feedback) {
        return FeedbackDTO.builder()
                .nota(feedback.getNota())
                .descricao(feedback.getDescricao())
                .nome(feedback.getContrato().getLocador().getNome())
                .build();
    }
}
