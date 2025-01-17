package com.dzialsprzedazy.dto;

import com.dzialsprzedazy.model.Status;

import java.util.Date;
import java.util.List;

public record ZamowienieDto(
        Long id,
        Long klientId,
        Long pracownikId,
        Status status,
        Date dataZalozenia,
        Date terminRealizacji,
        Double cenaLaczna,
        List<Long> towaryIds
) {


}
