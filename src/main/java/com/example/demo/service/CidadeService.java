package com.example.demo.service;

import com.example.demo.domain.cidade.Cidade;
import com.example.demo.repository.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CidadeService {

    @Autowired
    private CidadeRepository getrepository;

    //Retornando uma lista de cidade
    public List<Cidade> returnall() {
        return getrepository.findAll();
    }

    //Salvando uma cidade
    public Cidade saveCidade (Cidade cidade) {
        return getrepository.save(cidade);
    }

    //Procurando uma cidade por nome
    public Cidade returnName(String name) {
        return getrepository.findByName(name);
    }

}
