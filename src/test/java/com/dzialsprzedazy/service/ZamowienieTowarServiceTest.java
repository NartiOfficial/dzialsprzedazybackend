package com.dzialsprzedazy.service;

import com.dzialsprzedazy.dto.ZamowienieTowarDto;
import com.dzialsprzedazy.dto.ZamowienieTowarWithProductNameDto;
import com.dzialsprzedazy.model.Zamowienie;
import com.dzialsprzedazy.model.ZamowienieTowar;
import com.dzialsprzedazy.model.Towar;
import com.dzialsprzedazy.repository.ZamowienieRepository;
import com.dzialsprzedazy.repository.ZamowienieTowarRepository;
import com.dzialsprzedazy.repository.TowarRepository;
import jakarta.validation.ValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ZamowienieTowarServiceTest {

    private ZamowienieTowarRepository zamowienieTowarRepository;
    private ZamowienieRepository zamowienieRepository;
    private TowarRepository towarRepository;
    private ZamowienieTowarService zamowienieTowarService;

    @BeforeEach
    void setUp() {
        zamowienieTowarRepository = Mockito.mock(ZamowienieTowarRepository.class);
        zamowienieRepository = Mockito.mock(ZamowienieRepository.class);
        towarRepository = Mockito.mock(TowarRepository.class);
        zamowienieTowarService = new ZamowienieTowarService(zamowienieTowarRepository, zamowienieRepository, towarRepository);
    }

    @Test
    void findAll_shouldReturnPageOfZamowienieTowarDto() {
        Pageable pageable = PageRequest.of(0, 10);
        Zamowienie zamowienie = new Zamowienie(1L, null, null, null, Date.valueOf(LocalDate.now()), Date.valueOf(LocalDate.now().plusDays(7)), 100.0, List.of());
        Towar towar = new Towar(1L, "Produkt A", 50.0, "szt", 100.0, "Opis");
        ZamowienieTowar zamowienieTowar = new ZamowienieTowar(1L, zamowienie, towar, 5.0, 250.0);

        when(zamowienieTowarRepository.findAll(pageable)).thenReturn(new PageImpl<>(List.of(zamowienieTowar)));

        Page<ZamowienieTowarDto> result = zamowienieTowarService.findAll(pageable);

        assertEquals(1, result.getTotalElements());
        assertEquals(5.0, result.getContent().get(0).ilosc());
        verify(zamowienieTowarRepository, times(1)).findAll(pageable);
    }

    @Test
    void findById_shouldReturnZamowienieTowarDto_whenIdExists() {
        Zamowienie zamowienie = new Zamowienie(1L, null, null, null, Date.valueOf(LocalDate.now()), Date.valueOf(LocalDate.now().plusDays(7)), 100.0, List.of());
        Towar towar = new Towar(1L, "Produkt A", 50.0, "szt", 100.0, "Opis");
        ZamowienieTowar zamowienieTowar = new ZamowienieTowar(1L, zamowienie, towar, 5.0, 250.0);

        when(zamowienieTowarRepository.findById(1L)).thenReturn(Optional.of(zamowienieTowar));

        ZamowienieTowarDto result = zamowienieTowarService.findById(1L);

        assertEquals(5.0, result.ilosc());
        verify(zamowienieTowarRepository, times(1)).findById(1L);
    }

    @Test
    void create_shouldSaveAndReturnZamowienieTowarDto() {
        ZamowienieTowarDto zamowienieTowarDto = new ZamowienieTowarDto(null, 1L, 1L, 5.0, 250.0);
        Zamowienie zamowienie = new Zamowienie(1L, null, null, null, Date.valueOf(LocalDate.now()), Date.valueOf(LocalDate.now().plusDays(7)), 100.0, List.of());
        Towar towar = new Towar(1L, "Produkt A", 50.0, "szt", 100.0, "Opis");
        ZamowienieTowar zamowienieTowar = new ZamowienieTowar(null, zamowienie, towar, 5.0, 250.0);
        ZamowienieTowar savedZamowienieTowar = new ZamowienieTowar(1L, zamowienie, towar, 5.0, 250.0);

        when(zamowienieRepository.findById(1L)).thenReturn(Optional.of(zamowienie));
        when(towarRepository.findById(1L)).thenReturn(Optional.of(towar));
        when(zamowienieTowarRepository.save(any(ZamowienieTowar.class))).thenReturn(savedZamowienieTowar);

        ZamowienieTowarDto result = zamowienieTowarService.create(zamowienieTowarDto);

        assertEquals(1L, result.id());
        assertEquals(5.0, result.ilosc());
        verify(zamowienieTowarRepository, times(1)).save(any(ZamowienieTowar.class));
    }

    @Test
    void update_shouldUpdateAndReturnZamowienieTowarDto() {
        Zamowienie zamowienie = new Zamowienie(1L, null, null, null, Date.valueOf(LocalDate.now()), Date.valueOf(LocalDate.now().plusDays(7)), 100.0, List.of());
        Towar towar = new Towar(1L, "Produkt A", 50.0, "szt", 100.0, "Opis");
        ZamowienieTowar existingZamowienieTowar = new ZamowienieTowar(1L, zamowienie, towar, 5.0, 250.0);
        ZamowienieTowarDto zamowienieTowarDto = new ZamowienieTowarDto(1L, 1L, 1L, 10.0, 500.0);

        when(zamowienieTowarRepository.findById(1L)).thenReturn(Optional.of(existingZamowienieTowar));

        ZamowienieTowar updatedZamowienieTowar = new ZamowienieTowar(1L, zamowienie, towar, 10.0, 500.0);
        when(zamowienieTowarRepository.save(existingZamowienieTowar)).thenReturn(updatedZamowienieTowar);

        ZamowienieTowarDto result = zamowienieTowarService.update(1L, zamowienieTowarDto);

        assertEquals(10.0, result.ilosc());
        assertEquals(500.0, result.cenaSprzedazy());
        verify(zamowienieTowarRepository, times(1)).save(existingZamowienieTowar);
    }

    @Test
    void delete_shouldDeleteZamowienieTowar_whenIdExists() {
        when(zamowienieTowarRepository.existsById(1L)).thenReturn(true);

        zamowienieTowarService.delete(1L);

        verify(zamowienieTowarRepository, times(1)).deleteById(1L);
    }
}