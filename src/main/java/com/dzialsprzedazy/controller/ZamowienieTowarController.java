package com.dzialsprzedazy.controller;

import com.dzialsprzedazy.dto.ZamowienieTowarDto;
import com.dzialsprzedazy.dto.ZamowienieTowarWithProductNameDto;
import com.dzialsprzedazy.service.ZamowienieTowarService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/zamowienia-towary")
public class ZamowienieTowarController {

    private final ZamowienieTowarService zamowienieTowarService;

    ZamowienieTowarController(final ZamowienieTowarService zamowienieTowarService) {
        this.zamowienieTowarService = zamowienieTowarService;
    }

    @GetMapping
    public ResponseEntity<Page<ZamowienieTowarDto>> getAllZamowieniaTowary(Pageable pageable) {
        Page<ZamowienieTowarDto> zamowieniaTowary = zamowienieTowarService.findAll(pageable);
        return ResponseEntity.ok(zamowieniaTowary);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ZamowienieTowarDto> getZamowienieTowarById(@PathVariable Long id) {
        ZamowienieTowarDto zamowienieTowar = zamowienieTowarService.findById(id);
        return ResponseEntity.ok(zamowienieTowar);
    }

    @PostMapping
    public ResponseEntity<ZamowienieTowarDto> createZamowienieTowar(@Valid @RequestBody ZamowienieTowarDto zamowienieTowarDto) {
        ZamowienieTowarDto createdZamowienieTowar = zamowienieTowarService.create(zamowienieTowarDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdZamowienieTowar);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ZamowienieTowarDto> updateZamowienieTowar(@PathVariable Long id, @Valid @RequestBody ZamowienieTowarDto zamowienieTowarDto) {
        ZamowienieTowarDto updatedZamowienieTowar = zamowienieTowarService.update(id, zamowienieTowarDto);
        return ResponseEntity.ok(updatedZamowienieTowar);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteZamowienieTowar(@PathVariable Long id) {
        zamowienieTowarService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/zamowienie/{zamowienieId}")
    public ResponseEntity<Page<ZamowienieTowarWithProductNameDto>> getTowaryWithProductNameByZamowienieId(
            @PathVariable Long zamowienieId,
            Pageable pageable) {
        Page<ZamowienieTowarWithProductNameDto> towary = zamowienieTowarService.findWithProductNameByZamowienieId(zamowienieId, pageable);
        return ResponseEntity.ok(towary);
    }

}
