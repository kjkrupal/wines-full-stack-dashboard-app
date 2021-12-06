package com.kubeio.analysis.kubeioanalysis.controllers;

import com.kubeio.analysis.kubeioanalysis.exceptions.RecordNotFoundException;
import com.kubeio.analysis.kubeioanalysis.models.Wine;
import com.kubeio.analysis.kubeioanalysis.services.WineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wines")
public class WineController {

    @Autowired
    WineService wineService;

    @GetMapping
    public ResponseEntity<List<Wine>> fetchAllWines() {
        List<Wine> wines = wineService.fetchAllWines();
        return new ResponseEntity<List<Wine>>(wines, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Wine> fetchEmployeeById(@PathVariable("id") Long id) throws RecordNotFoundException {
        Wine wine = wineService.fetchWineById(id);
        return new ResponseEntity<Wine>(wine, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Wine> createWine(@RequestBody Wine wine) throws RecordNotFoundException {
        Wine createdWine = wineService.createWine(wine);
        return new ResponseEntity<Wine>(createdWine, new HttpHeaders(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Wine> updateWine(@PathVariable("id") Long id, @RequestBody Wine wine) throws RecordNotFoundException {
        Wine updatedWine = wineService.updateWine(id, wine);
        return new ResponseEntity<Wine>(updatedWine, new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteWineById(@PathVariable("id") Long id) throws RecordNotFoundException {
        wineService.deleteWineById(id);
        return HttpStatus.NO_CONTENT;
    }
}
