package com.project.cruiser.services;

import com.project.cruiser.entity.Cruise;
import com.project.cruiser.entity.User;
import com.project.cruiser.repository.CruiseRepository;
import com.project.cruiser.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CruiseService {

    private final CruiseRepository cruiseRepository;

    @Autowired
    public CruiseService(CruiseRepository cruiseRepository) {
        this.cruiseRepository = cruiseRepository;
    }

    public Cruise findById(Long id){
        return Optional.of(cruiseRepository.findById(id))
                .get()
                .orElseThrow(RuntimeException::new);
    }

    public List<Cruise> findAll() {
        return Optional.of(cruiseRepository.findAll())
                .orElseThrow(RuntimeException::new);
    }

    public Cruise save(Cruise cruise) {
        cruise.setFreePlaces(cruise.getPassCapacity());
        return cruiseRepository.save(cruise);
    }

    public Cruise update(Cruise cruise) {
        return cruiseRepository.save(cruise);
    }

    public void deleteById(Long id) {
        cruiseRepository.deleteById(id);
    }

    public boolean hasFreePlace(Cruise cruise) {
        //todo Optional
        return cruiseRepository.getById(cruise.getId()).getFreePlaces() > 0;

    }
}
