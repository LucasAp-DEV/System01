package demo.TCC.service;

import demo.TCC.domain.contrato.ContratoDTO;
import demo.TCC.domain.contrato.Contrato;
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

    public List<ContratoDTO> returnall() {
        List<Contrato> contratos = repository.findAll();
        List<ContratoDTO> contratosDTO = new ArrayList<>();
        for (Contrato contrato : contratos) {
            ContratoDTO contratoDTO = new ContratoDTO(
                    contrato.getDescricao(),
                    contrato.getData(),
                    contrato.getStatus(),
                    contrato.getLocal().getId(),
                    contrato.getUser().getNome()
            );
            contratosDTO.add(contratoDTO);
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
        contrato.setDescricao(data.getDescricao());
        contrato.setStatus(data.getStatus());
        contrato.setData(data.getData());
        contrato.setLocal(data.getLocal());
        contrato.setUser(data.getUser());
        repository.save(contrato);
        return ResponseEntity.status(HttpStatus.OK).body("Update Realizado");
    }

    private void validate(Contrato data) {
        if (Objects.isNull(data.getStatus())) throw new RuntimeException("Status é requirido");
        if (Objects.isNull(data.getDescricao())) throw new RuntimeException("Status é requirido");
        if (Objects.isNull(data.getData())) throw new RuntimeException("Status é requirido");
        if (Objects.isNull(data.getUser())) throw new RuntimeException("Status é requirido");
        if (Objects.isNull(data.getLocal())) throw new RuntimeException("Status é requirido");
    }
}


