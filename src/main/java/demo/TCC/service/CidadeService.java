package demo.TCC.service;

import demo.TCC.domain.cidade.Cidade;
import demo.TCC.repository.CidadeRepository;
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

    public Cidade returnName(String name) {
        return getrepository.findByName(name);
    }

}
