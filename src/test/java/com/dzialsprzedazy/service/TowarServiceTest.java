package com.dzialsprzedazy.service;

import com.dzialsprzedazy.dto.TowarDto;
import com.dzialsprzedazy.model.Towar;
import com.dzialsprzedazy.repository.TowarRepository;
import jakarta.validation.ValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TowarServiceTest {

    private TowarRepository towarRepository;
    private TowarService towarService;

    @BeforeEach
    void setUp() {
        towarRepository = Mockito.mock(TowarRepository.class);
        towarService = new TowarService(towarRepository);
    }

    @Test
    void findAll_shouldReturnPageOfTowarDto() {
        Pageable pageable = PageRequest.of(0, 10);
        Towar towar = new Towar(1L, "Produkt A", 100.0, "szt", 10.0, "Opis produktu A");
        when(towarRepository.findAll(pageable)).thenReturn(new PageImpl<>(List.of(towar)));

        Page<TowarDto> result = towarService.findAll(pageable);

        assertEquals(1, result.getTotalElements());
        assertEquals("Produkt A", result.getContent().get(0).nazwaProduktu());
        verify(towarRepository, times(1)).findAll(pageable);
    }

    @Test
    void findById_shouldReturnTowarDto_whenIdExists() {
        Towar towar = new Towar(1L, "Produkt A", 100.0, "szt", 10.0, "Opis produktu A");
        when(towarRepository.findById(1L)).thenReturn(Optional.of(towar));

        TowarDto result = towarService.findById(1L);

        assertEquals("Produkt A", result.nazwaProduktu());
        verify(towarRepository, times(1)).findById(1L);
    }

    @Test
    void findById_shouldThrowValidationException_whenIdDoesNotExist() {
        when(towarRepository.findById(1L)).thenReturn(Optional.empty());

        ValidationException exception = assertThrows(ValidationException.class, () -> towarService.findById(1L));
        assertEquals("Towar o podanym ID nie istnieje: 1", exception.getMessage());
    }

    @Test
    void create_shouldSaveAndReturnTowarDto() {
        TowarDto towarDto = new TowarDto(null, "Produkt A", 100.0, "szt", 10.0, "Opis produktu A");
        Towar towar = new Towar(null, "Produkt A", 100.0, "szt", 10.0, "Opis produktu A");
        Towar savedTowar = new Towar(1L, "Produkt A", 100.0, "szt", 10.0, "Opis produktu A");
        when(towarRepository.save(any(Towar.class))).thenReturn(savedTowar);

        TowarDto result = towarService.create(towarDto);

        assertEquals(1L, result.id());
        assertEquals("Produkt A", result.nazwaProduktu());
        verify(towarRepository, times(1)).save(any(Towar.class));
    }

    @Test
    void update_shouldUpdateAndReturnTowarDto() {
        Towar existingTowar = new Towar(1L, "Produkt A", 100.0, "szt", 10.0, "Opis produktu A");
        TowarDto towarDto = new TowarDto(null, "Produkt B", 200.0, "kg", 20.0, "Opis produktu B");
        Towar updatedTowar = new Towar(1L, "Produkt B", 200.0, "kg", 20.0, "Opis produktu B");
        when(towarRepository.findById(1L)).thenReturn(Optional.of(existingTowar));
        when(towarRepository.save(existingTowar)).thenReturn(updatedTowar);

        TowarDto result = towarService.update(1L, towarDto);

        assertEquals("Produkt B", result.nazwaProduktu());
        assertEquals(200.0, result.cenaJednostkowa());
        verify(towarRepository, times(1)).findById(1L);
        verify(towarRepository, times(1)).save(existingTowar);
    }

    @Test
    void update_shouldThrowValidationException_whenIdDoesNotExist() {
        TowarDto towarDto = new TowarDto(null, "Produkt B", 200.0, "kg", 20.0, "Opis produktu B");
        when(towarRepository.findById(1L)).thenReturn(Optional.empty());

        ValidationException exception = assertThrows(ValidationException.class, () -> towarService.update(1L, towarDto));
        assertEquals("Towar o podanym ID nie istnieje: 1", exception.getMessage());
    }

    @Test
    void delete_shouldDeleteTowar_whenIdExists() {
        when(towarRepository.existsById(1L)).thenReturn(true);

        towarService.delete(1L);

        verify(towarRepository, times(1)).deleteById(1L);
    }

    @Test
    void delete_shouldThrowValidationException_whenIdDoesNotExist() {
        when(towarRepository.existsById(1L)).thenReturn(false);

        ValidationException exception = assertThrows(ValidationException.class, () -> towarService.delete(1L));
        assertEquals("Towar o podanym ID nie istnieje: 1", exception.getMessage());
    }
}