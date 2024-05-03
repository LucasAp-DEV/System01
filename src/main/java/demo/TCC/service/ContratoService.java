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
                        contrato.getLocatario().getNome(),
                        contrato.getLocatario().getTelephone(),
                        contrato.getLocal().getPrice(),
                        contrato.getLocal().getEndereco(),
                        contrato.getLocal().getCidade().getName()
                );
                contratosDTO.add(contratoDTO);
            }
        }
        return contratosDTO;
    }


    public void saveContrato(Contrato contrato) {
        repository.save(contrato);
    }

    public Contrato returnId(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Contrato não Encontrado"));
    }

    public ContratoDTO findByUserId(Long id) {
        var contrato = returnId(id);
        return converte(contrato);
    }

    public ContratoDTO converte(Contrato contrato) {
        return ContratoDTO.builder()
                .id(contrato.getId())
                .data(contrato.getData())
                .status(contrato.getStatus())
                .local(contrato.getLocal().getId())
                .locador(contrato.getLocador().getNome())
                .locatario(contrato.getLocatario().getNome())
                .telephone(contrato.getLocatario().getTelephone())
                .price(contrato.getLocal().getPrice())
                .endereco(contrato.getLocal().getEndereco())
                .cidade(contrato.getLocal().getCidade().getName())
                .build();
    }

    public ResponseEntity<String> updateById(Long id, Contrato data) {
        var contrato = returnId(id);
//        validate(data);
        contrato.setStatus(data.getStatus());
        repository.save(contrato);
        return ResponseEntity.status(HttpStatus.OK).body("Update Realizado");
    }

//    private void validate(Contrato data) {
//        if (Objects.isNull(data.getStatus())) throw new RuntimeException("Status é requirido");
//        if (Objects.isNull(data.getData())) throw new RuntimeException("Status é requirido");
//        if (Objects.isNull(data.getLocal())) throw new RuntimeException("Status é requirido");
//    }

    public List<ContratoDTO> returnByLocatarioOuLocador(Long userId) {
        List<Contrato> contratosLocatario = repository.findByLocatarioId(userId);
        List<Contrato> contratosLocador = repository.findByLocadorId(userId);

        List<ContratoDTO> contratosDTO = new ArrayList<>();
        for (Contrato contrato : contratosLocatario) {
            if (contrato != null) {
                ContratoDTO contratoDTO = new ContratoDTO(
                        contrato.getId(),
                        contrato.getData(),
                        contrato.getStatus(),
                        contrato.getLocal().getId(),
                        contrato.getLocador().getNome(),
                        contrato.getLocatario().getNome(),
                        contrato.getLocatario().getTelephone(),
                        contrato.getLocal().getPrice(),
                        contrato.getLocal().getEndereco(),
                        contrato.getLocal().getCidade().getName()
                );
                contratosDTO.add(contratoDTO);
            }
        }
        for (Contrato contrato : contratosLocador) {
            if (contrato != null) {
                ContratoDTO contratoDTO = new ContratoDTO(
                        contrato.getId(),
                        contrato.getData(),
                        contrato.getStatus(),
                        contrato.getLocal().getId(),
                        contrato.getLocador().getNome(),
                        contrato.getLocatario().getNome(),
                        contrato.getLocatario().getTelephone(),
                        contrato.getLocal().getPrice(),
                        contrato.getLocal().getEndereco(),
                        contrato.getLocal().getCidade().getName()
                );
                contratosDTO.add(contratoDTO);
            }
        }
        return contratosDTO;
    }

}


