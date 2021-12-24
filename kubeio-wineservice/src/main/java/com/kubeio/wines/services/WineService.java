package com.kubeio.wines.services;

import com.kubeio.wines.exceptions.RecordNotFoundException;
import com.kubeio.wines.models.Wine;
import com.kubeio.wines.repository.WineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class WineService {

    @Autowired
    WineRepository wineRepository;

    public List<Wine> fetchAllWines() {
        List<Wine> wines = wineRepository.findAll();
        if(wines.size() > 0) {
            return wines;
        }
        return new ArrayList<Wine>();
    }

    public Wine fetchWineById(Long id) throws RecordNotFoundException {
        Optional<Wine> wine = wineRepository.findById(id);
        if(wine.isPresent()) {
            return wine.get();
        }
        else {
            throw new RecordNotFoundException("Wine record with id " + id + " does not exist.");
        }
    }

    public Wine createWine(Wine wine) {
        wine.setUuid(UUID.randomUUID());
        wineRepository.save(wine);
        return wine;
    }

    public Wine updateWine(Long id, Wine newWine) throws RecordNotFoundException {
        Optional<Wine> wineRecord = wineRepository.findById(id);

        if(wineRecord.isPresent()) {
            Wine wine = wineRecord.get();
            wine.setCountry(newWine.getCountry());
            wine.setWinery(newWine.getWinery());
            wine.setPoints(newWine.getPoints());
            wine.setDescription(newWine.getDescription());
            wine.setPrice(newWine.getPrice());
            wine.setVariety(newWine.getVariety());

            wine = wineRepository.save(wine);

            return wine;
        }
        else {
            throw new RecordNotFoundException("Wine record with id " + id + " does not exist.");
        }
    }

    public void deleteWineById(Long id) throws RecordNotFoundException {
        Optional<Wine> wine = wineRepository.findById(id);

        if(wine.isPresent()) {
            wineRepository.deleteById(id);
        }
        else {
            throw new RecordNotFoundException("Wine record with id " + id + " does not exist.");
        }
    }
}
