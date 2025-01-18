package com.dzialsprzedazy.dto;

public record ZamowienieTowarWithProductNameDto(
        Long id,
        Long zamowienieId,
        Long towarId,
        String nazwaProduktu,
        Double ilosc,
        Double cenaSprzedazy
) {
}
