package com.dzialsprzedazy.controller;

import com.dzialsprzedazy.dto.ZamowienieDto;
import com.dzialsprzedazy.service.ZamowienieService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/zamowienia")
public class ZamowienieController {

    private final ZamowienieService zamowienieService;

    ZamowienieController(final ZamowienieService zamowienieService) {
        this.zamowienieService = zamowienieService;
    }

    @GetMapping
    public ResponseEntity<Page<ZamowienieDto>> getAllZamowienia(Pageable pageable) {
        Page<ZamowienieDto> zamowienia = zamowienieService.findAll(pageable);
        return ResponseEntity.ok(zamowienia);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ZamowienieDto> getZamowienieById(@PathVariable Long id) {
        ZamowienieDto zamowienie = zamowienieService.findById(id);
        return ResponseEntity.ok(zamowienie);
    }

    @PostMapping
    public ResponseEntity<ZamowienieDto> createZamowienie(@Valid @RequestBody ZamowienieDto zamowienieDto) {
        ZamowienieDto createdZamowienie = zamowienieService.create(zamowienieDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdZamowienie);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ZamowienieDto> updateZamowienie(@PathVariable Long id, @Valid @RequestBody ZamowienieDto zamowienieDto) {
        ZamowienieDto updatedZamowienie = zamowienieService.update(id, zamowienieDto);
        return ResponseEntity.ok(updatedZamowienie);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteZamowienie(@PathVariable Long id) {
        zamowienieService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
