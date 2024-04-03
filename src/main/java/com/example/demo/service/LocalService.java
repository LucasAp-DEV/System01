package com.example.demo.service;

import com.example.demo.domain.local.Local;
import com.example.demo.domain.local.LocalDTO;
import com.example.demo.repository.LocalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


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

    //Salvando Local
    public void saveLocal(Local local) {
        repository.save(local);
    }

    //Procurando por ID e retornando o DTO
    public LocalDTO findByLocalId(Long id) {
        var local = repository.findById(id).orElseThrow(() -> new RuntimeException("Local não encontrado"));
        return converte(local);
    }

    //Procurando por ID
    public Local findById(Long id) {
        return repository.findById(id).orElseThrow(()-> new RuntimeException("Local não contrado"));
    }

    //Deletando Local
    public void dellLocal(Long id) {
        var dellLocal = findById(id);
        repository.delete(dellLocal);
    }

    //Atualizando o Local
    public void updateLocal(Long id, Local data){
        var local = findById(id);
        validate(data);
        local.setPrice(data.getPrice());
        local.setEndereco(data.getEndereco());
        local.setDescricao(data.getDescricao());

        repository.save(local);
    }

    //Validando Campos que saõ necessarios editar
    private void validate(Local data) {
        if (Objects.isNull(data.getPrice()))
            throw new RuntimeException("Status é requirido");
        if (Objects.isNull(data.getEndereco()))
            throw new RuntimeException("Status é requirido");
        if (Objects.isNull(data.getDescricao()))
            throw new RuntimeException("Status é requirido");
    }

    //Convertendo entidade para LocalDTO (Response)
    public LocalDTO converte(Local local) {
        return LocalDTO.builder()
                .id(local.getId())
                .price(local.getPrice())
                .descricao(local.getDescricao())
                .endereco(local.getEndereco())
                .userId(local.getUser().getId())
                .build();
    }
}
