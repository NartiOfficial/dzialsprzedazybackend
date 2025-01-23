package com.dzialsprzedazy.service;

import com.dzialsprzedazy.dto.KlientDto;
import com.dzialsprzedazy.model.Klient;
import com.dzialsprzedazy.repository.KlientRepository;
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

class KlientServiceTest {

    private KlientRepository klientRepository;
    private KlientService klientService;

    @BeforeEach
    void setUp() {
        klientRepository = Mockito.mock(KlientRepository.class);
        klientService = new KlientService(klientRepository);
    }

    @Test
    void findAll_shouldReturnPageOfKlientDto() {
        Pageable pageable = PageRequest.of(0, 10);
        Klient klient = new Klient(1L, "Jan", "Kowalski", "jan@example.com", "Ulica", "1", "00-001", "Miasto");
        when(klientRepository.findAll(pageable)).thenReturn(new PageImpl<>(List.of(klient)));

        Page<KlientDto> result = klientService.findAll(pageable);

        assertEquals(1, result.getTotalElements());
        assertEquals("Jan", result.getContent().get(0).imie());
        verify(klientRepository, times(1)).findAll(pageable);
    }

    @Test
    void findById_shouldReturnKlientDto_whenIdExists() {
        Klient klient = new Klient(1L, "Jan", "Kowalski", "jan@example.com", "Ulica", "1", "00-001", "Miasto");
        when(klientRepository.findById(1L)).thenReturn(Optional.of(klient));

        KlientDto result = klientService.findById(1L);

        assertEquals("Jan", result.imie());
        verify(klientRepository, times(1)).findById(1L);
    }

    @Test
    void findById_shouldThrowValidationException_whenIdDoesNotExist() {
        when(klientRepository.findById(1L)).thenReturn(Optional.empty());

        ValidationException exception = assertThrows(ValidationException.class, () -> klientService.findById(1L));
        assertEquals("Klient o podanym ID nie istnieje: 1", exception.getMessage());
    }

    @Test
    void create_shouldSaveAndReturnKlientDto() {
        KlientDto klientDto = new KlientDto(null, "Jan", "Kowalski", "jan@example.com", "Ulica", "1", "00-001", "Miasto");
        Klient klient = new Klient(null, "Jan", "Kowalski", "jan@example.com", "Ulica", "1", "00-001", "Miasto");
        Klient savedKlient = new Klient(1L, "Jan", "Kowalski", "jan@example.com", "Ulica", "1", "00-001", "Miasto");
        when(klientRepository.save(any(Klient.class))).thenReturn(savedKlient);

        KlientDto result = klientService.create(klientDto);

        assertEquals(1L, result.id());
        assertEquals("Jan", result.imie());
        verify(klientRepository, times(1)).save(any(Klient.class));
    }

    @Test
    void update_shouldUpdateAndReturnKlientDto() {
        Klient existingKlient = new Klient(1L, "Jan", "Kowalski", "jan@example.com", "Ulica", "1", "00-001", "Miasto");
        KlientDto klientDto = new KlientDto(null, "Adam", "Nowak", "adam@example.com", "Nowa", "2", "11-111", "NoweMiasto");
        Klient updatedKlient = new Klient(1L, "Adam", "Nowak", "adam@example.com", "Nowa", "2", "11-111", "NoweMiasto");
        when(klientRepository.findById(1L)).thenReturn(Optional.of(existingKlient));
        when(klientRepository.save(existingKlient)).thenReturn(updatedKlient);

        KlientDto result = klientService.update(1L, klientDto);

        assertEquals("Adam", result.imie());
        assertEquals("Nowak", result.nazwisko());
        verify(klientRepository, times(1)).findById(1L);
        verify(klientRepository, times(1)).save(existingKlient);
    }

    @Test
    void update_shouldThrowValidationException_whenIdDoesNotExist() {
        KlientDto klientDto = new KlientDto(null, "Adam", "Nowak", "adam@example.com", "Nowa", "2", "11-111", "NoweMiasto");
        when(klientRepository.findById(1L)).thenReturn(Optional.empty());

        ValidationException exception = assertThrows(ValidationException.class, () -> klientService.update(1L, klientDto));
        assertEquals("Klient o podanym ID nie istnieje: 1", exception.getMessage());
    }

    @Test
    void delete_shouldDeleteKlient_whenIdExists() {
        when(klientRepository.existsById(1L)).thenReturn(true);

        klientService.delete(1L);

        verify(klientRepository, times(1)).deleteById(1L);
    }

    @Test
    void delete_shouldThrowValidationException_whenIdDoesNotExist() {
        when(klientRepository.existsById(1L)).thenReturn(false);

        ValidationException exception = assertThrows(ValidationException.class, () -> klientService.delete(1L));
        assertEquals("Klient o podanym ID nie istnieje: 1", exception.getMessage());
    }
}