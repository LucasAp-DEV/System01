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

    public List<Cidade> returnall() {
        return getrepository.findAll();
    }

    public Cidade saveCidade (Cidade cidade) {
        return getrepository.save(cidade);
    }

//    public Cidade returnId(Long id) {
//        return getrepository.findById(id).orElseThrow(()-> new RuntimeException("Cidade não encontrada"));
//    }
    public Cidade returnName(String name) {
        return getrepository.findByName(name);
    }

//    public void deleteCidade(Long id) {
//        var cidade = returnId(id);
//        getrepository.delete(cidade);
//    }
}
