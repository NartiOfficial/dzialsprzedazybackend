package com.dzialsprzedazy.dto;

import com.dzialsprzedazy.model.Stanowisko;

public record PracownikDto(
        Long id,
        String imie,
        String nazwisko,
        Stanowisko stanowisko,
        String numerTelefonu,
        String email
) {
}
