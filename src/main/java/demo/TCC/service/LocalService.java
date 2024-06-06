package demo.TCC.service;

import demo.TCC.domain.feedback.Feedback;
import demo.TCC.domain.feedback.FeedbackDTO;
import demo.TCC.domain.image.Image;
import demo.TCC.domain.local.Local;
import demo.TCC.domain.local.LocalDTO;
import demo.TCC.repository.LocalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class LocalService {

    private final LocalRepository repository;

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


    public List<LocalDTO> returnLocalFilter(LocalDate data) {
        List<Local> localList = repository.findByContratosDataNot(data);
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

    public ResponseEntity<String> updateLocal(Long id, Local data) {
        try {
            var local = findById(id);
            local.setPrice(data.getPrice());
            local.setDescricao(data.getDescricao());
            local.setEndereco(data.getEndereco());
            local.setDisponibilidade(data.getDisponibilidade());
            repository.save(local);

            return ResponseEntity.status(HttpStatus.OK).body("Atualizaçao realizada");

        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Autenticaçao falhou");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao atualizar o local");
        }
    }

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
                .status(local.getStatus())
                .locatarioName(local.getLocatario().getNome())
                .locatarioId(local.getLocatario().getId())
                .locatarioTell(local.getLocatario().getTelefone())
                .cidade(local.getCidade().getName())
                .cidadeId(local.getCidade().getId())
                .disponibilidade(local.getDisponibilidade())
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
