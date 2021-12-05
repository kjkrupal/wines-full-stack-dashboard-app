package com.kubeio.analysis.kubeioanalysis.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WineController {

    @GetMapping
    public List<String> getWines() {
        return null;
    }
}
