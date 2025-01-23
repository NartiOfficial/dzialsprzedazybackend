package com.dzialsprzedazy.service;

import com.dzialsprzedazy.dto.ZamowienieDto;
import com.dzialsprzedazy.model.Klient;
import com.dzialsprzedazy.model.Pracownik;
import com.dzialsprzedazy.model.Status;
import com.dzialsprzedazy.model.Zamowienie;
import com.dzialsprzedazy.repository.KlientRepository;
import com.dzialsprzedazy.repository.PracownikRepository;
import com.dzialsprzedazy.repository.ZamowienieRepository;
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

class ZamowienieServiceTest {

    private ZamowienieRepository zamowienieRepository;
    private KlientRepository klientRepository;
    private PracownikRepository pracownikRepository;
    private ZamowienieService zamowienieService;

    @BeforeEach
    void setUp() {
        zamowienieRepository = Mockito.mock(ZamowienieRepository.class);
        klientRepository = Mockito.mock(KlientRepository.class);
        pracownikRepository = Mockito.mock(PracownikRepository.class);
        zamowienieService = new ZamowienieService(zamowienieRepository, klientRepository, pracownikRepository);
    }

    @Test
    void findById_shouldReturnZamowienieDto_whenIdExists() {
        Zamowienie zamowienie = new Zamowienie(
                1L,
                new Klient(),
                new Pracownik(),
                Status.W_TRAKCIE,
                Date.valueOf(LocalDate.now()),
                Date.valueOf(LocalDate.now().plusDays(7)),
                100.0,
                List.of()
        );

        when(zamowienieRepository.findById(1L)).thenReturn(Optional.of(zamowienie));

        ZamowienieDto result = zamowienieService.findById(1L);

        assertEquals(Status.W_TRAKCIE, result.status());
        verify(zamowienieRepository, times(1)).findById(1L);
    }

    @Test
    void create_shouldSaveAndReturnZamowienieDto() {
        ZamowienieDto zamowienieDto = new ZamowienieDto(
                null,
                1L,
                1L,
                Status.W_TRAKCIE,
                Date.valueOf(LocalDate.now()),
                Date.valueOf(LocalDate.now().plusDays(7)),
                100.0,
                List.of()
        );
        Klient klient = new Klient();
        Pracownik pracownik = new Pracownik();
        Zamowienie zamowienie = new Zamowienie(
                null,
                klient,
                pracownik,
                Status.W_TRAKCIE,
                Date.valueOf(LocalDate.now()),
                Date.valueOf(LocalDate.now().plusDays(7)),
                100.0,
                List.of()
        );
        Zamowienie savedZamowienie = new Zamowienie(
                1L,
                klient,
                pracownik,
                Status.W_TRAKCIE,
                Date.valueOf(LocalDate.now()),
                Date.valueOf(LocalDate.now().plusDays(7)),
                100.0,
                List.of()
        );

        when(klientRepository.findById(1L)).thenReturn(Optional.of(klient));
        when(pracownikRepository.findById(1L)).thenReturn(Optional.of(pracownik));
        when(zamowienieRepository.save(any(Zamowienie.class))).thenReturn(savedZamowienie);

        ZamowienieDto result = zamowienieService.create(zamowienieDto);

        assertEquals(1L, result.id());
        assertEquals(Status.W_TRAKCIE, result.status());
        verify(zamowienieRepository, times(1)).save(any(Zamowienie.class));
    }
}