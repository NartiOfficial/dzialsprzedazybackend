package com.dzialsprzedazy.service;

import com.dzialsprzedazy.dto.KlientDto;
import com.dzialsprzedazy.model.Klient;
import com.dzialsprzedazy.repository.KlientRepository;
import jakarta.validation.ValidationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class KlientService {

    private final KlientRepository klientRepository;

    KlientService(final KlientRepository klientRepository) {
        this.klientRepository = klientRepository;
    }

    public Page<KlientDto> findAll(Pageable pageable) {
        return klientRepository.findAll(pageable)
                .map(this::mapToDto);
    }

    public KlientDto findById(Long id) {
        Klient klient = klientRepository.findById(id)
                .orElseThrow(() -> new ValidationException("Klient o podanym ID nie istnieje: " + id));
        return mapToDto(klient);
    }

    public KlientDto create(KlientDto klientDto) {
        validateKlientDto(klientDto);
        Klient klient = mapToEntity(klientDto);
        Klient savedKlient = klientRepository.save(klient);
        return mapToDto(savedKlient);
    }

    public KlientDto update(Long id, KlientDto klientDto) {
        validateKlientDto(klientDto);
        Klient existingKlient = klientRepository.findById(id)
                .orElseThrow(() -> new ValidationException("Klient o podanym ID nie istnieje: " + id));

        existingKlient.setImie(klientDto.imie());
        existingKlient.setNazwisko(klientDto.nazwisko());
        existingKlient.setEmail(klientDto.email());
        existingKlient.setUlica(klientDto.ulica());
        existingKlient.setNumer(klientDto.numer());
        existingKlient.setKodPocztowy(klientDto.kodPocztowy());
        existingKlient.setMiasto(klientDto.miasto());

        Klient updatedKlient = klientRepository.save(existingKlient);
        return mapToDto(updatedKlient);
    }

    public void delete(Long id) {
        if (!klientRepository.existsById(id)) {
            throw new ValidationException("Klient o podanym ID nie istnieje: " + id);
        }
        klientRepository.deleteById(id);
    }

    private KlientDto mapToDto(Klient klient) {
        return new KlientDto(
                klient.getId(),
                klient.getImie(),
                klient.getNazwisko(),
                klient.getEmail(),
                klient.getUlica(),
                klient.getNumer(),
                klient.getKodPocztowy(),
                klient.getMiasto()
                );
    }

    private Klient mapToEntity(KlientDto klientDto) {
        return new Klient(
                klientDto.id(),
                klientDto.imie(),
                klientDto.nazwisko(),
                klientDto.email(),
                klientDto.ulica(),
                klientDto.numer(),
                klientDto.kodPocztowy(),
                klientDto.miasto()
                );
    }

    private void validateKlientDto(KlientDto klientDto) {
        if (klientDto.imie() == null || klientDto.imie().isBlank()) {
            throw new ValidationException("Imię nie może być puste.");
        }
        if (klientDto.nazwisko() == null || klientDto.nazwisko().isBlank()) {
            throw new ValidationException("Nazwisko nie może być puste.");
        }
        if (klientDto.email() == null || klientDto.email().isBlank()) {
            throw new ValidationException("Email nie może być pusty.");
        }
        if (!klientDto.email().contains("@")) {
            throw new ValidationException("Email musi zawierać znak '@'.");
        }
    }
}
