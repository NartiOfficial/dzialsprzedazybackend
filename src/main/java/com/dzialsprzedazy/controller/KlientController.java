package com.dzialsprzedazy.controller;

import com.dzialsprzedazy.model.Klient;
import com.dzialsprzedazy.service.KlientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/klienci")
public class KlientController {

    private final KlientService klientService;

    public KlientController(KlientService klientService) {
        this.klientService = klientService;
    }

    @GetMapping
    public List<Klient> getAllKlienci() {
        return klientService.getAllKlienci();
    }

    @GetMapping("/{id}")
    public Klient getKlientById(@PathVariable Long id) {
        return klientService.getKlientById(id);
    }

    @PostMapping
    public ResponseEntity<Klient> createKlient(@RequestBody Klient klient) {
        return ResponseEntity.ok(klientService.createKlient(klient));
    }
}
