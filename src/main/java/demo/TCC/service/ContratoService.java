package demo.TCC.service;

import demo.TCC.domain.contrato.ContratoDTO;
import demo.TCC.domain.contrato.Contrato;
import demo.TCC.domain.contrato.UpdateContratoDTO;
import demo.TCC.domain.local.Local;
import demo.TCC.exception.AppException;
import demo.TCC.repository.ContratoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ContratoService {

    @Autowired
    private ContratoRepository repository;

//    public void saveContrato(Contrato contrato) {
//        LocalDate data = contrato.getData();
//        Local local = contrato.getLocal();
//
//        Optional<Contrato> existContrato = repository.findByDataAndLocal(data, local);
//
//        if (existContrato.isPresent()) {
//            throw new AppException("Ja existe um contrato com essa data.");
//        }
//        repository.save(contrato);
//    }

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
                .locadorId(contrato.getLocador().getId())
                .locatario(contrato.getLocatario().getNome())
                .locatarioId(contrato.getLocatario().getId())
                .telephone(contrato.getLocatario().getTelefone())
                .price(contrato.getLocal().getPrice())
                .endereco(contrato.getLocal().getEndereco())
                .cidade(contrato.getLocal().getCidade().getName())
                .build();
    }

    public ResponseEntity<String> updateById(Long id, UpdateContratoDTO data) {
        var contrato = returnId(id);
        contrato.setStatus(data.status());
        repository.save(contrato);
        return ResponseEntity.status(HttpStatus.OK).body("Contrato Salvo");
    }

    public List<ContratoDTO> returnByLocatarioOuLocador(Long userId) {
//        List<Contrato> contratosLocatario = repository.findByLocatarioId(userId);
//        List<Contrato> contratosLocador = repository.findByLocadorId(userId);
        List<Contrato> findByUser = repository.findByLocatario_IdOrLocador_Id(userId, userId);

        List<ContratoDTO> contratosDTO = new ArrayList<>();
        for (Contrato contrato : findByUser) {
            if (contrato != null) {
                ContratoDTO contratoDTO = new ContratoDTO(
                        contrato.getId(),
                        contrato.getData(),
                        contrato.getStatus(),
                        contrato.getLocal().getId(),
                        contrato.getLocador().getNome(),
                        contrato.getLocatario().getNome(),
                        contrato.getLocatario().getTelefone(),
                        contrato.getLocal().getPrice(),
                        contrato.getLocal().getEndereco(),
                        contrato.getLocal().getCidade().getName(),
                        contrato.getLocador().getId(),
                        contrato.getLocatario().getId()
                );
                contratosDTO.add(contratoDTO);
            }
        }
        return contratosDTO;
    }

}


