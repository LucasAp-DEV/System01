package com.example.demo.service;

import com.example.demo.model.Sector;
import com.example.demo.repository.SectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SectorService {

    @Autowired
    private SectorRepository repository;

    public Sector saveSector (Sector sector) {
        return repository.save(sector);
    }

    public List<Sector> returnSector () {
        return repository.findAll();
    }

    public Optional<Sector> returnSectorId(Long id) {
        return repository.findById(id);
    }

   public void dellSector(Optional<Sector> sector) {
        repository.delete(sector.get());
   }

}
