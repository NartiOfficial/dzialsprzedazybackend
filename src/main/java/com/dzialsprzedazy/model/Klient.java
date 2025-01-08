package com.dzialsprzedazy.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class Klient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imie;

    private String nazwisko;

    private String email;

    private String ulica;

    private String numer;

    private String kodPocztowy;

    private String miasto;

    @OneToMany(mappedBy = "klient")
    private List<Zamowienie> zamowienia;
}