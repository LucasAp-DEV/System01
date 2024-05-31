package demo.TCC.service;

import demo.TCC.domain.cidade.Cidade;
import demo.TCC.repository.CidadeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CidadeService {

    private final CidadeRepository getrepository;

    public List<Cidade> returnall() {
        return getrepository.findAll();
    }

    public void saveCidade (Cidade cidade) {
        getrepository.save(cidade);
    }

    public Cidade returnName(String name) {
        return getrepository.findByName(name);
    }

}

