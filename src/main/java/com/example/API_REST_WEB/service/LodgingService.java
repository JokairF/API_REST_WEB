package com.example.API_REST_WEB.service;

import com.example.API_REST_WEB.entity.Lodging;
import com.example.API_REST_WEB.repository.LodgingRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LodgingService {
    private final LodgingRepository lodgingRepository;

    public List<Lodging> getAllLodgings() {
        return lodgingRepository.findAll();
    }

    public Lodging getLodgingById(Long id) {
        return lodgingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Logement non trouvé"));
    }

    public Lodging createLodging(Lodging lodging) {
        return lodgingRepository.save(lodging);
    }

    public Lodging updateLodging(Long id, Lodging updatedLodging) {
        Lodging lodging = getLodgingById(id);
        lodging.setName(updatedLodging.getName());
        lodging.setAddress(updatedLodging.getAddress());
        return lodgingRepository.save(lodging);
    }

    public void deleteLodging(Long id) {
        lodgingRepository.deleteById(id);
    }
}
