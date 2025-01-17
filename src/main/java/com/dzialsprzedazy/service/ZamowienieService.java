package com.dzialsprzedazy.service;

import com.dzialsprzedazy.dto.ZamowienieDto;
import com.dzialsprzedazy.model.Zamowienie;
import com.dzialsprzedazy.repository.ZamowienieRepository;
import com.dzialsprzedazy.repository.KlientRepository;
import com.dzialsprzedazy.repository.PracownikRepository;
import jakarta.validation.ValidationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ZamowienieService {

    private final ZamowienieRepository zamowienieRepository;
    private final KlientRepository klientRepository;
    private final PracownikRepository pracownikRepository;

    ZamowienieService(final ZamowienieRepository zamowienieRepository, final KlientRepository klientRepository, final PracownikRepository pracownikRepository) {
        this.zamowienieRepository = zamowienieRepository;
        this.klientRepository = klientRepository;
        this.pracownikRepository = pracownikRepository;
    }

    public Page<ZamowienieDto> findAll(Pageable pageable) {
        return zamowienieRepository.findAll(pageable)
                .map(this::mapToDto);
    }

    public ZamowienieDto findById(Long id) {
        Zamowienie zamowienie = zamowienieRepository.findById(id)
                .orElseThrow(() -> new ValidationException("Zamówienie o podanym ID nie istnieje: " + id));
        return mapToDto(zamowienie);
    }

    public ZamowienieDto create(ZamowienieDto zamowienieDto) {
        validateZamowienieDto(zamowienieDto);
        Zamowienie zamowienie = mapToEntity(zamowienieDto);
        Zamowienie savedZamowienie = zamowienieRepository.save(zamowienie);
        return mapToDto(savedZamowienie);
    }

    public ZamowienieDto update(Long id, ZamowienieDto zamowienieDto) {
        validateZamowienieDto(zamowienieDto);
        Zamowienie existingZamowienie = zamowienieRepository.findById(id)
                .orElseThrow(() -> new ValidationException("Zamówienie o podanym ID nie istnieje: " + id));

        existingZamowienie.setStatus(zamowienieDto.status());
        existingZamowienie.setDataZalozenia(zamowienieDto.dataZalozenia());
        existingZamowienie.setTerminRealizacji(zamowienieDto.terminRealizacji());
        existingZamowienie.setCenaLaczna(zamowienieDto.cenaLaczna());

        Zamowienie updatedZamowienie = zamowienieRepository.save(existingZamowienie);
        return mapToDto(updatedZamowienie);
    }

    public void delete(Long id) {
        if (!zamowienieRepository.existsById(id)) {
            throw new ValidationException("Zamówienie o podanym ID nie istnieje: " + id);
        }
        zamowienieRepository.deleteById(id);
    }

    private ZamowienieDto mapToDto(Zamowienie zamowienie) {
        List<Long> towaryIds = zamowienie.getTowary().stream()
                .map(zamowienieTowar -> {
                    if (zamowienieTowar.getTowar() == null || zamowienieTowar.getTowar().getId() == null) {
                        throw new IllegalArgumentException("Towar or Towar ID is null in ZamowienieTowar");
                    }
                    return zamowienieTowar.getTowar().getId();
                })
                .collect(Collectors.toList());

        return new ZamowienieDto(
                zamowienie.getId(),
                zamowienie.getKlient().getId(),
                zamowienie.getPracownik().getId(),
                zamowienie.getStatus(),
                zamowienie.getDataZalozenia(),
                zamowienie.getTerminRealizacji(),
                zamowienie.getCenaLaczna(),
                towaryIds
        );
    }

    private Zamowienie mapToEntity(ZamowienieDto zamowienieDto) {
        Zamowienie zamowienie = new Zamowienie();
        zamowienie.setKlient(klientRepository.findById(zamowienieDto.klientId())
                .orElseThrow(() -> new ValidationException("Klient o podanym ID nie istnieje: " + zamowienieDto.klientId())));
        zamowienie.setPracownik(pracownikRepository.findById(zamowienieDto.pracownikId())
                .orElseThrow(() -> new ValidationException("Pracownik o podanym ID nie istnieje: " + zamowienieDto.pracownikId())));
        zamowienie.setStatus(zamowienieDto.status());
        zamowienie.setDataZalozenia(zamowienieDto.dataZalozenia());
        zamowienie.setTerminRealizacji(zamowienieDto.terminRealizacji());
        zamowienie.setCenaLaczna(zamowienieDto.cenaLaczna());
        return zamowienie;
    }

    private void validateZamowienieDto(ZamowienieDto zamowienieDto) {
        if (zamowienieDto.klientId() == null) {
            throw new ValidationException("Klient ID nie może być pusty.");
        }
        if (zamowienieDto.pracownikId() == null) {
            throw new ValidationException("Pracownik ID nie może być pusty.");
        }
        if (zamowienieDto.dataZalozenia() == null) {
            throw new ValidationException("Data założenia nie może być pusta.");
        }
    }
}
