package com.kubeio.search.controller;

import com.fasterxml.jackson.databind.introspect.TypeResolutionContext;
import com.kubeio.search.models.Wine;
import com.kubeio.search.services.WineService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @PostMapping("/refresh")
    public ResponseEntity searchByField(@RequestBody Optional<Wine> wine) {
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
