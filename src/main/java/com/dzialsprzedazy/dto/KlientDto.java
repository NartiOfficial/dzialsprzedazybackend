package com.dzialsprzedazy.dto;

public record KlientDto(
        Long id,
        String imie,
        String nazwisko,
        String email,
        String ulica,
        String numer,
        String kodPocztowy,
        String miasto
) {
}
