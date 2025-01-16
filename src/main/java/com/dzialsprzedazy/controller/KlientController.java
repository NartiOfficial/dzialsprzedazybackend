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
    public ResponseEntity<List<Klient>> getAllKlienci() {
        return ResponseEntity.ok(klientService.getAllKlienci());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Klient> getKlientById(@PathVariable Long id) {
        return ResponseEntity.ok(klientService.getKlientById(id));
    }

    @PostMapping
    public ResponseEntity<Klient> createKlient(@RequestBody Klient klient) {
        return ResponseEntity.ok(klientService.createKlient(klient));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Klient> updateKlient(@PathVariable Long id, @RequestBody Klient klient) {
        return ResponseEntity.ok(klientService.updateKlient(id, klient));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteKlient(@PathVariable Long id) {
        klientService.deleteKlient(id);
        return ResponseEntity.noContent().build();
    }
}