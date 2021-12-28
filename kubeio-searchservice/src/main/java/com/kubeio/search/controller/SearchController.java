package com.kubeio.search.controller;

import com.kubeio.search.models.Wine;
import com.kubeio.search.services.WineService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchController {

    final WineService wineService;
    public SearchController(WineService wineService, RabbitTemplate rabbitTemplate) {
        this.wineService = wineService;
    }

    @GetMapping("/wines")
    public ResponseEntity<List<Wine>> searchWineByField(@RequestParam("q") String query) {
        List<Wine> wines = wineService.findWinesForQuery(query);
        return new ResponseEntity<List<Wine>>(wines, new HttpHeaders(), HttpStatus.OK);
    }
}
