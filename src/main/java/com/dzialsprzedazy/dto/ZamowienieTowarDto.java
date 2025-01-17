package com.dzialsprzedazy.dto;

public record ZamowienieTowarDto(
        Long id,
        Long zamowienieId,
        Long towarId,
        Double ilosc,
        Double cenaSprzedazy
) {
}
