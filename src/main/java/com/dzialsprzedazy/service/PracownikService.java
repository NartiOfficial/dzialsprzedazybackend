package com.dzialsprzedazy.service;

import com.dzialsprzedazy.dto.PracownikDto;
import com.dzialsprzedazy.model.Pracownik;
import com.dzialsprzedazy.repository.PracownikRepository;
import jakarta.validation.ValidationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PracownikService {

    private final PracownikRepository pracownikRepository;

    PracownikService(final PracownikRepository pracownikRepository) {
        this.pracownikRepository = pracownikRepository;
    }

    public Page<PracownikDto> findAll(Pageable pageable) {
        return pracownikRepository.findAll(pageable)
                .map(this::mapToDto);
    }

    public PracownikDto findById(Long id) {
        Pracownik pracownik = pracownikRepository.findById(id)
                .orElseThrow(() -> new ValidationException("Pracownik o podanym ID nie istnieje: " + id));
        return mapToDto(pracownik);
    }

    public PracownikDto create(PracownikDto pracownikDto) {
        validatePracownikDto(pracownikDto);
        Pracownik pracownik = mapToEntity(pracownikDto);
        Pracownik savedPracownik = pracownikRepository.save(pracownik);
        return mapToDto(savedPracownik);
    }

    public PracownikDto update(Long id, PracownikDto pracownikDto) {
        validatePracownikDto(pracownikDto);
        Pracownik existingPracownik = pracownikRepository.findById(id)
                .orElseThrow(() -> new ValidationException("Pracownik o podanym ID nie istnieje: " + id));

        existingPracownik.setImie(pracownikDto.imie());
        existingPracownik.setNazwisko(pracownikDto.nazwisko());
        existingPracownik.setStanowisko(pracownikDto.stanowisko());
        existingPracownik.setNumerTelefonu(pracownikDto.numerTelefonu());
        existingPracownik.setEmail(pracownikDto.email());

        Pracownik updatedPracownik = pracownikRepository.save(existingPracownik);
        return mapToDto(updatedPracownik);
    }

    public void delete(Long id) {
        if (!pracownikRepository.existsById(id)) {
            throw new ValidationException("Pracownik o podanym ID nie istnieje: " + id);
        }
        pracownikRepository.deleteById(id);
    }

    private PracownikDto mapToDto(Pracownik pracownik) {
        return new PracownikDto(
                pracownik.getId(),
                pracownik.getImie(),
                pracownik.getNazwisko(),
                pracownik.getStanowisko(),
                pracownik.getNumerTelefonu(),
                pracownik.getEmail()
                );
    }

    private Pracownik mapToEntity(PracownikDto pracownikDto) {
        Pracownik pracownik = new Pracownik();
        pracownik.setId(pracownikDto.id());
        pracownik.setImie(pracownikDto.imie());
        pracownik.setNazwisko(pracownikDto.nazwisko());
        pracownik.setStanowisko(pracownikDto.stanowisko());
        pracownik.setNumerTelefonu(pracownikDto.numerTelefonu());
        pracownik.setEmail(pracownikDto.email());
        return pracownik;
    }

    private void validatePracownikDto(PracownikDto pracownikDto) {
        if (pracownikDto.imie() == null || pracownikDto.imie().isBlank()) {
            throw new ValidationException("Imię nie może być puste.");
        }
        if (pracownikDto.nazwisko() == null || pracownikDto.nazwisko().isBlank()) {
            throw new ValidationException("Nazwisko nie może być puste.");
        }
        if (pracownikDto.email() == null || pracownikDto.email().isBlank()) {
            throw new ValidationException("Email nie może być pusty.");
        }
        if (!pracownikDto.email().contains("@")) {
            throw new ValidationException("Email musi zawierać znak '@'.");
        }
    }
}
