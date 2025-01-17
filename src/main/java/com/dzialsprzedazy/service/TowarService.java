package com.dzialsprzedazy.service;

import com.dzialsprzedazy.dto.TowarDto;
import com.dzialsprzedazy.model.Towar;
import com.dzialsprzedazy.repository.TowarRepository;
import jakarta.validation.ValidationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TowarService {

    private final TowarRepository towarRepository;

    TowarService(final TowarRepository towarRepository) {
        this.towarRepository = towarRepository;
    }

    public Page<TowarDto> findAll(Pageable pageable) {
        return towarRepository.findAll(pageable)
                .map(this::mapToDto);
    }

    public TowarDto findById(Long id) {
        Towar towar = towarRepository.findById(id)
                .orElseThrow(() -> new ValidationException("Towar o podanym ID nie istnieje: " + id));
        return mapToDto(towar);
    }

    public TowarDto create(TowarDto towarDto) {
        validateTowarDto(towarDto);
        Towar towar = mapToEntity(towarDto);
        Towar savedTowar = towarRepository.save(towar);
        return mapToDto(savedTowar);
    }

    public TowarDto update(Long id, TowarDto towarDto) {
        validateTowarDto(towarDto);
        Towar existingTowar = towarRepository.findById(id)
                .orElseThrow(() -> new ValidationException("Towar o podanym ID nie istnieje: " + id));

        existingTowar.setNazwaProduktu(towarDto.nazwaProduktu());
        existingTowar.setCenaJednostkowa(towarDto.cenaJednostkowa());
        existingTowar.setJednostkaMiara(towarDto.jednostkaMiara());
        existingTowar.setStanMagazynowy(towarDto.stanMagazynowy());
        existingTowar.setOpis(towarDto.opis());

        Towar updatedTowar = towarRepository.save(existingTowar);
        return mapToDto(updatedTowar);
    }

    public void delete(Long id) {
        if (!towarRepository.existsById(id)) {
            throw new ValidationException("Towar o podanym ID nie istnieje: " + id);
        }
        towarRepository.deleteById(id);
    }

    private TowarDto mapToDto(Towar towar) {
        return new TowarDto(
                towar.getId(),
                towar.getNazwaProduktu(),
                towar.getCenaJednostkowa(),
                towar.getJednostkaMiara(),
                towar.getStanMagazynowy(),
                towar.getOpis()
                );
    }

    private Towar mapToEntity(TowarDto towarDto) {
        Towar towar = new Towar();
        towar.setId(towarDto.id());
        towar.setNazwaProduktu(towarDto.nazwaProduktu());
        towar.setCenaJednostkowa(towarDto.cenaJednostkowa());
        towar.setJednostkaMiara(towarDto.jednostkaMiara());
        towar.setStanMagazynowy(towarDto.stanMagazynowy());
        towar.setOpis(towarDto.opis());
        return towar;
    }

    private void validateTowarDto(TowarDto towarDto) {
        if (towarDto.nazwaProduktu() == null || towarDto.nazwaProduktu().isBlank()) {
            throw new ValidationException("Nazwa produktu nie może być pusta.");
        }
        if (towarDto.cenaJednostkowa() == null || towarDto.cenaJednostkowa() < 0) {
            throw new ValidationException("Cena jednostkowa musi być większa lub równa zero.");
        }
        if (towarDto.stanMagazynowy() == null || towarDto.stanMagazynowy() < 0) {
            throw new ValidationException("Stan magazynowy musi być większy lub równy zero.");
        }
    }
}
