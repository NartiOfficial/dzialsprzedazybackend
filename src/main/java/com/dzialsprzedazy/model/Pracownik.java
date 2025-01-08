package com.dzialsprzedazy.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
class Pracownik {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imie;

    private String nazwisko;

    @Enumerated(EnumType.STRING)
    private Stanowisko stanowisko;

    private String numerTelefonu;

    private String email;

    @OneToMany(mappedBy = "pracownik")
    private List<Zamowienie> zamowienia;
}