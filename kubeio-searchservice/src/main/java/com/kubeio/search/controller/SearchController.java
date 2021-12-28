package com.kubeio.search.controller;

import com.kubeio.search.models.Wine;
import com.kubeio.search.services.WineService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchController {

    private final WineService wineService;

    public SearchController(WineService wineService) {
        this.wineService = wineService;
    }

    @GetMapping("/wines")
    public ResponseEntity<List<Wine>> searchWineByField(@RequestParam("q") String query) {
        List<Wine> wines = wineService.findWinesForQuery(query);
        return new ResponseEntity<List<Wine>>(wines, new HttpHeaders(), HttpStatus.OK);
    }
}
