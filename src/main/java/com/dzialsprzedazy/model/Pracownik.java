package com.dzialsprzedazy.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class Pracownik {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imie;

    private String nazwisko;

    @Enumerated(EnumType.STRING)
    private Stanowisko stanowisko;

    private String numerTelefonu;

    private String email;

    @OneToMany(mappedBy = "pracownik", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH}, orphanRemoval = false)
    private List<Zamowienie> zamowienia;

    public Pracownik(final Long id, final String imie, final String nazwisko, final Stanowisko stanowisko, final String numerTelefonu, final String email) {
        this.id = id;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.stanowisko = stanowisko;
        this.numerTelefonu = numerTelefonu;
        this.email = email;
    }

    public Pracownik() {

    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(final String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(final String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public Stanowisko getStanowisko() {
        return stanowisko;
    }

    public void setStanowisko(final Stanowisko stanowisko) {
        this.stanowisko = stanowisko;
    }

    public String getNumerTelefonu() {
        return numerTelefonu;
    }

    public void setNumerTelefonu(final String numerTelefonu) {
        this.numerTelefonu = numerTelefonu;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public List<Zamowienie> getZamowienia() {
        return zamowienia;
    }

    public void setZamowienia(final List<Zamowienie> zamowienia) {
        this.zamowienia = zamowienia;
    }
}