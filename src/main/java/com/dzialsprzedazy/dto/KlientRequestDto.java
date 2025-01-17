package com.dzialsprzedazy.dto;

public record KlientRequestDto(
        String imie,
        String nazwisko,
        String email,
        String ulica,
        String numer,
        String kodPocztowy,
        String miasto
) {
}
