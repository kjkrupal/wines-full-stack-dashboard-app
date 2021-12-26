package com.kubeio.wines.services;

import com.kubeio.wines.dto.WineEvent;
import com.kubeio.wines.exceptions.RecordNotFoundException;
import com.kubeio.wines.models.Wine;
import com.kubeio.wines.repository.WineRepository;
import com.kubeio.wines.publishers.WinePublisher;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class WineService {

    private final WineRepository wineRepository;
    private final WinePublisher winePublisher;

    public WineService(WineRepository wineRepository, WinePublisher winePublisher) {
        this.wineRepository = wineRepository;
        this.winePublisher = winePublisher;
    }

    @Cacheable(value = "Wine")
    public List<Wine> fetchAllWines() {
        List<Wine> wines = wineRepository.findAll();
        if(wines.size() > 0) {
            return wines;
        }
        return new ArrayList<Wine>();
    }

    @Cacheable(value = "Wine", key = "#id")
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
        winePublisher.publishWineEvent(wine, WineEvent.CREATE);
        return wine;
    }

    @CachePut(value = "Wine", key = "#id")
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
            winePublisher.publishWineEvent(wine, WineEvent.UPDATE);
            return wine;
        }
        else {
            throw new RecordNotFoundException("Wine record with id " + id + " does not exist.");
        }
    }

    @CacheEvict(value = "Wine", key = "#id")
    public void deleteWineById(Long id) throws RecordNotFoundException {
        Optional<Wine> wine = wineRepository.findById(id);

        if(wine.isPresent()) {
            wineRepository.deleteById(id);
            winePublisher.publishWineEvent(wine.get(), WineEvent.DELETE);
        }
        else {
            throw new RecordNotFoundException("Wine record with id " + id + " does not exist.");
        }
    }

}
