package demo.TCC.service;

import demo.TCC.domain.contrato.ContratoDTO;
import demo.TCC.domain.contrato.Contrato;
import demo.TCC.domain.user.User;
import demo.TCC.repository.ContratoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ContratoService {
    @Autowired
    private ContratoRepository repository;

    //Mudar para buscar por uma lista de ID
    public List<ContratoDTO> returnall() {
        List<Contrato> contratos = repository.findAll();
        List<ContratoDTO> contratosDTO = new ArrayList<>();
        for (Contrato contrato : contratos) {
            if (contrato != null && contrato.getLocador() != null) {
                ContratoDTO contratoDTO = new ContratoDTO(
                        contrato.getId(),
                        contrato.getData(),
                        contrato.getStatus(),
                        contrato.getLocal().getId(),
                        contrato.getLocador().getNome(),
                        contrato.getLocatario().getNome()
                );
                contratosDTO.add(contratoDTO);
            }
        }
        return contratosDTO;
    }


    public Contrato saveContrato(Contrato contrato) {
        return repository.save(contrato);
    }

    public Contrato returnId(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Contrato não Encontrado"));
    }

    public ResponseEntity<String> updateById(Long id, Contrato data) {
        var contrato = returnId(id);
        validate(data);
        contrato.setStatus(data.getStatus());
        contrato.setData(data.getData());
        contrato.setLocal(data.getLocal());
        repository.save(contrato);
        return ResponseEntity.status(HttpStatus.OK).body("Update Realizado");
    }

    private void validate(Contrato data) {
        if (Objects.isNull(data.getStatus())) throw new RuntimeException("Status é requirido");
        if (Objects.isNull(data.getData())) throw new RuntimeException("Status é requirido");
        if (Objects.isNull(data.getLocal())) throw new RuntimeException("Status é requirido");
    }

    public List<ContratoDTO> returnByLocador(Long locadorId) {
        List<Contrato> contratos = repository.findAllByLocadorId(locadorId);
        List<ContratoDTO> contratosDTO = new ArrayList<>();
        for (Contrato contrato : contratos) {
            if (contrato != null) {
                ContratoDTO contratoDTO = new ContratoDTO(
                        contrato.getId(),
                        contrato.getData(),
                        contrato.getStatus(),
                        contrato.getLocal().getId(),
                        contrato.getLocador().getNome(),
                        contrato.getLocatario().getNome()
                );
                contratosDTO.add(contratoDTO);
            }
        }
        return contratosDTO;
    }


}


