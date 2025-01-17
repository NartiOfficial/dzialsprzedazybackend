package com.dzialsprzedazy.controller;

import com.dzialsprzedazy.dto.TowarDto;
import com.dzialsprzedazy.service.TowarService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/towary")
public class TowarController {

    private final TowarService towarService;

    TowarController(final TowarService towarService) {
        this.towarService = towarService;
    }

    @GetMapping
    public ResponseEntity<Page<TowarDto>> getAllTowary(Pageable pageable) {
        Page<TowarDto> towary = towarService.findAll(pageable);
        return ResponseEntity.ok(towary);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TowarDto> getTowarById(@PathVariable Long id) {
        TowarDto towar = towarService.findById(id);
        return ResponseEntity.ok(towar);
    }

    @PostMapping
    public ResponseEntity<TowarDto> createTowar(@Valid @RequestBody TowarDto towarDto) {
        TowarDto createdTowar = towarService.create(towarDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTowar);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TowarDto> updateTowar(@PathVariable Long id, @Valid @RequestBody TowarDto towarDto) {
        TowarDto updatedTowar = towarService.update(id, towarDto);
        return ResponseEntity.ok(updatedTowar);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTowar(@PathVariable Long id) {
        towarService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
