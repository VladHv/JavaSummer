package com.project.cruiser.services;

import com.project.cruiser.entity.Cruise;
import com.project.cruiser.entity.User;
import com.project.cruiser.repository.CruiseRepository;
import com.project.cruiser.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public List<Cruise> findAll(LocalDateTime start, LocalDateTime end) {
        if (start == null) {
            start = cruiseRepository.findAll().stream()
                    .map(Cruise::getStart)
                    .min(LocalDateTime::compareTo)
                    .orElseThrow(RuntimeException::new).minusMinutes(1L);
        }

        if (end == null) {
            end = cruiseRepository.findAll().stream()
                    .map(Cruise::getEnd)
                    .max(LocalDateTime::compareTo)
                    .orElseThrow(RuntimeException::new).plusMinutes(1L);
        }

        final LocalDateTime finalStart = start;
        final LocalDateTime finalEnd = end;
        return cruiseRepository.findAll().stream()
                .filter(cruise -> cruise.getStart().isAfter(finalStart))
                .filter(cruise -> cruise.getEnd().isBefore(finalEnd))
                .collect(Collectors.toList());
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
