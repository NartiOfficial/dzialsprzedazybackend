package com.dzialsprzedazy.controller;

import com.dzialsprzedazy.dto.PracownikDto;
import com.dzialsprzedazy.service.PracownikService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pracownicy")
public class PracownikController {

    private final PracownikService pracownikService;

    PracownikController(final PracownikService pracownikService) {
        this.pracownikService = pracownikService;
    }

    @GetMapping
    public ResponseEntity<Page<PracownikDto>> getAllPracownicy(Pageable pageable) {
        Page<PracownikDto> pracownicy = pracownikService.findAll(pageable);
        return ResponseEntity.ok(pracownicy);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PracownikDto> getPracownikById(@PathVariable Long id) {
        PracownikDto pracownik = pracownikService.findById(id);
        return ResponseEntity.ok(pracownik);
    }

    @PostMapping
    public ResponseEntity<PracownikDto> createPracownik(@Valid @RequestBody PracownikDto pracownikDto) {
        PracownikDto createdPracownik = pracownikService.create(pracownikDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPracownik);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PracownikDto> updatePracownik(@PathVariable Long id, @Valid @RequestBody PracownikDto pracownikDto) {
        PracownikDto updatedPracownik = pracownikService.update(id, pracownikDto);
        return ResponseEntity.ok(updatedPracownik);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePracownik(@PathVariable Long id) {
        pracownikService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
