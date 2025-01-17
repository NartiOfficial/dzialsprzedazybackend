package com.dzialsprzedazy.service;

import com.dzialsprzedazy.dto.ZamowienieTowarDto;
import com.dzialsprzedazy.model.Zamowienie;
import com.dzialsprzedazy.model.ZamowienieTowar;
import com.dzialsprzedazy.model.Towar;
import com.dzialsprzedazy.repository.ZamowienieRepository;
import com.dzialsprzedazy.repository.ZamowienieTowarRepository;
import com.dzialsprzedazy.repository.TowarRepository;
import jakarta.validation.ValidationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ZamowienieTowarService {

    private final ZamowienieTowarRepository zamowienieTowarRepository;
    private final ZamowienieRepository zamowienieRepository;
    private final TowarRepository towarRepository;

    ZamowienieTowarService(final ZamowienieTowarRepository zamowienieTowarRepository, final ZamowienieRepository zamowienieRepository, final TowarRepository towarRepository) {
        this.zamowienieTowarRepository = zamowienieTowarRepository;
        this.zamowienieRepository = zamowienieRepository;
        this.towarRepository = towarRepository;
    }

    public Page<ZamowienieTowarDto> findAll(Pageable pageable) {
        return zamowienieTowarRepository.findAll(pageable)
                .map(this::mapToDto);
    }

    public ZamowienieTowarDto findById(Long id) {
        ZamowienieTowar zamowienieTowar = zamowienieTowarRepository.findById(id)
                .orElseThrow(() -> new ValidationException("ZamówienieTowar o podanym ID nie istnieje: " + id));
        return mapToDto(zamowienieTowar);
    }

    public ZamowienieTowarDto create(ZamowienieTowarDto zamowienieTowarDto) {
        validateZamowienieTowarDto(zamowienieTowarDto);
        ZamowienieTowar zamowienieTowar = mapToEntity(zamowienieTowarDto);
        ZamowienieTowar savedZamowienieTowar = zamowienieTowarRepository.save(zamowienieTowar);
        return mapToDto(savedZamowienieTowar);
    }

    public ZamowienieTowarDto update(Long id, ZamowienieTowarDto zamowienieTowarDto) {
        validateZamowienieTowarDto(zamowienieTowarDto);
        ZamowienieTowar existingZamowienieTowar = zamowienieTowarRepository.findById(id)
                .orElseThrow(() -> new ValidationException("ZamówienieTowar o podanym ID nie istnieje: " + id));

        existingZamowienieTowar.setIlosc(zamowienieTowarDto.ilosc());
        existingZamowienieTowar.setCenaSprzedazy(zamowienieTowarDto.cenaSprzedazy());

        ZamowienieTowar updatedZamowienieTowar = zamowienieTowarRepository.save(existingZamowienieTowar);
        return mapToDto(updatedZamowienieTowar);
    }

    public void delete(Long id) {
        if (!zamowienieTowarRepository.existsById(id)) {
            throw new ValidationException("ZamówienieTowar o podanym ID nie istnieje: " + id);
        }
        zamowienieTowarRepository.deleteById(id);
    }

    private ZamowienieTowarDto mapToDto(ZamowienieTowar zamowienieTowar) {
        return new ZamowienieTowarDto(
                zamowienieTowar.getId(),
                zamowienieTowar.getZamowienie().getId(),
                zamowienieTowar.getTowar().getId(),
                zamowienieTowar.getIlosc(),
                zamowienieTowar.getCenaSprzedazy()
                );
    }

    private ZamowienieTowar mapToEntity(ZamowienieTowarDto zamowienieTowarDto) {
        Zamowienie zamowienie = zamowienieRepository.findById(zamowienieTowarDto.zamowienieId())
                .orElseThrow(() -> new ValidationException("Zamówienie o podanym ID nie istnieje: " + zamowienieTowarDto.zamowienieId()));
        Towar towar = towarRepository.findById(zamowienieTowarDto.towarId())
                .orElseThrow(() -> new ValidationException("Towar o podanym ID nie istnieje: " + zamowienieTowarDto.towarId()));

        ZamowienieTowar zamowienieTowar = new ZamowienieTowar();
        zamowienieTowar.setZamowienie(zamowienie);
        zamowienieTowar.setTowar(towar);
        zamowienieTowar.setIlosc(zamowienieTowarDto.ilosc());
        zamowienieTowar.setCenaSprzedazy(zamowienieTowarDto.cenaSprzedazy());
        return zamowienieTowar;
    }

    private void validateZamowienieTowarDto(ZamowienieTowarDto zamowienieTowarDto) {
        if (zamowienieTowarDto.zamowienieId() == null) {
            throw new ValidationException("Zamówienie ID nie może być puste.");
        }
        if (zamowienieTowarDto.towarId() == null) {
            throw new ValidationException("Towar ID nie może być pusty.");
        }
        if (zamowienieTowarDto.ilosc() == null || zamowienieTowarDto.ilosc() <= 0) {
            throw new ValidationException("Ilość musi być większa od zera.");
        }
        if (zamowienieTowarDto.cenaSprzedazy() == null || zamowienieTowarDto.cenaSprzedazy() < 0) {
            throw new ValidationException("Cena sprzedaży musi być większa lub równa zero.");
        }
    }
}
