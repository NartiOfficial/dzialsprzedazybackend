package com.dzialsprzedazy.service;

import com.dzialsprzedazy.dto.PracownikDto;
import com.dzialsprzedazy.model.Pracownik;
import com.dzialsprzedazy.model.Stanowisko;
import com.dzialsprzedazy.repository.PracownikRepository;
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

class PracownikServiceTest {

    private PracownikRepository pracownikRepository;
    private PracownikService pracownikService;

    @BeforeEach
    void setUp() {
        pracownikRepository = Mockito.mock(PracownikRepository.class);
        pracownikService = new PracownikService(pracownikRepository);
    }

    @Test
    void findAll_shouldReturnPageOfPracownikDto() {
        Pageable pageable = PageRequest.of(0, 10);
        Pracownik pracownik = new Pracownik(1L, "Jan", "Kowalski", Stanowisko.PRACOWNIK, "123456789", "jan@example.com");
        when(pracownikRepository.findAll(pageable)).thenReturn(new PageImpl<>(List.of(pracownik)));

        Page<PracownikDto> result = pracownikService.findAll(pageable);

        assertEquals(1, result.getTotalElements());
        assertEquals("Jan", result.getContent().get(0).imie());
        verify(pracownikRepository, times(1)).findAll(pageable);
    }

    @Test
    void findById_shouldReturnPracownikDto_whenIdExists() {
        Pracownik pracownik = new Pracownik(1L, "Jan", "Kowalski", Stanowisko.PRACOWNIK, "123456789", "jan@example.com");
        when(pracownikRepository.findById(1L)).thenReturn(Optional.of(pracownik));

        PracownikDto result = pracownikService.findById(1L);

        assertEquals("Jan", result.imie());
        verify(pracownikRepository, times(1)).findById(1L);
    }

    @Test
    void findById_shouldThrowValidationException_whenIdDoesNotExist() {
        when(pracownikRepository.findById(1L)).thenReturn(Optional.empty());

        ValidationException exception = assertThrows(ValidationException.class, () -> pracownikService.findById(1L));
        assertEquals("Pracownik o podanym ID nie istnieje: 1", exception.getMessage());
    }

    @Test
    void create_shouldSaveAndReturnPracownikDto() {
        PracownikDto pracownikDto = new PracownikDto(null, "Jan", "Kowalski", Stanowisko.PRACOWNIK, "123456789", "jan@example.com");
        Pracownik pracownik = new Pracownik(null, "Jan", "Kowalski", Stanowisko.PRACOWNIK, "123456789", "jan@example.com");
        Pracownik savedPracownik = new Pracownik(1L, "Jan", "Kowalski", Stanowisko.PRACOWNIK, "123456789", "jan@example.com");
        when(pracownikRepository.save(any(Pracownik.class))).thenReturn(savedPracownik);

        PracownikDto result = pracownikService.create(pracownikDto);

        assertEquals(1L, result.id());
        assertEquals("Jan", result.imie());
        verify(pracownikRepository, times(1)).save(any(Pracownik.class));
    }

    @Test
    void update_shouldUpdateAndReturnPracownikDto() {
        Pracownik existingPracownik = new Pracownik(1L, "Jan", "Kowalski", Stanowisko.PRACOWNIK, "123456789", "jan@example.com");
        PracownikDto pracownikDto = new PracownikDto(null, "Adam", "Nowak", Stanowisko.PRACOWNIK, "987654321", "adam@example.com");
        Pracownik updatedPracownik = new Pracownik(1L, "Adam", "Nowak", Stanowisko.PRACOWNIK, "987654321", "adam@example.com");
        when(pracownikRepository.findById(1L)).thenReturn(Optional.of(existingPracownik));
        when(pracownikRepository.save(existingPracownik)).thenReturn(updatedPracownik);

        PracownikDto result = pracownikService.update(1L, pracownikDto);

        assertEquals("Adam", result.imie());
        assertEquals("Nowak", result.nazwisko());
        verify(pracownikRepository, times(1)).findById(1L);
        verify(pracownikRepository, times(1)).save(existingPracownik);
    }

    @Test
    void update_shouldThrowValidationException_whenIdDoesNotExist() {
        PracownikDto pracownikDto = new PracownikDto(null, "Adam", "Nowak", Stanowisko.PRACOWNIK, "987654321", "adam@example.com");
        when(pracownikRepository.findById(1L)).thenReturn(Optional.empty());

        ValidationException exception = assertThrows(ValidationException.class, () -> pracownikService.update(1L, pracownikDto));
        assertEquals("Pracownik o podanym ID nie istnieje: 1", exception.getMessage());
    }

    @Test
    void delete_shouldDeletePracownik_whenIdExists() {
        when(pracownikRepository.existsById(1L)).thenReturn(true);

        pracownikService.delete(1L);

        verify(pracownikRepository, times(1)).deleteById(1L);
    }

    @Test
    void delete_shouldThrowValidationException_whenIdDoesNotExist() {
        when(pracownikRepository.existsById(1L)).thenReturn(false);

        ValidationException exception = assertThrows(ValidationException.class, () -> pracownikService.delete(1L));
        assertEquals("Pracownik o podanym ID nie istnieje: 1", exception.getMessage());
    }
}