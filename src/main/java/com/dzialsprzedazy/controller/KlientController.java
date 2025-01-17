package com.dzialsprzedazy.controller;

import com.dzialsprzedazy.dto.KlientDto;
import com.dzialsprzedazy.service.KlientService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/klienci")
public class KlientController {

    private final KlientService klientService;

    KlientController(final KlientService klientService) {
        this.klientService = klientService;
    }

    // GET: Pobranie wszystkich klientów
    @GetMapping
    public ResponseEntity<Page<KlientDto>> getAllKlienci(Pageable pageable) {
        Page<KlientDto> klienci = klientService.findAll(pageable);
        return ResponseEntity.ok(klienci);
    }

    // GET: Pobranie klienta po ID
    @GetMapping("/{id}")
    public ResponseEntity<KlientDto> getKlientById(@PathVariable Long id) {
        KlientDto klient = klientService.findById(id);
        return ResponseEntity.ok(klient);
    }

    // POST: Tworzenie nowego klienta
    @PostMapping
    public ResponseEntity<KlientDto> createKlient(@Valid @RequestBody KlientDto klientDto) {
        KlientDto createdKlient = klientService.create(klientDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdKlient);
    }

    // PUT: Aktualizacja istniejącego klienta
    @PutMapping("/{id}")
    public ResponseEntity<KlientDto> updateKlient(@PathVariable Long id, @Valid @RequestBody KlientDto klientDto) {
        KlientDto updatedKlient = klientService.update(id, klientDto);
        return ResponseEntity.ok(updatedKlient);
    }

    // DELETE: Usuwanie klienta po ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteKlient(@PathVariable Long id) {
        klientService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
