package com.dzialsprzedazy.dto;

public record TowarDto(
        Long id,
        String nazwaProduktu,
        Double cenaJednostkowa,
        String jednostkaMiara,
        Double stanMagazynowy,
        String opis
) {
}
