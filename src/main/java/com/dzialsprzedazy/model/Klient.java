package com.dzialsprzedazy.model;

import jakarta.persistence.CascadeType;
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

    @OneToMany(mappedBy = "klient", cascade = CascadeType.ALL)
    private List<Zamowienie> zamowienia;

    public Klient(final Long id, final String imie, final String nazwisko, final String email, final String ulica, final String numer, final String kodPocztowy, final String miasto) {
        this.id = id;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.email = email;
        this.ulica = ulica;
        this.numer = numer;
        this.kodPocztowy = kodPocztowy;
        this.miasto = miasto;
    }

    public Klient() {

    }

    public Long getId() {
        return id;
    }

    public Klient setId(final Long id) {
        this.id = id;
        return this;
    }

    public String getImie() {
        return imie;
    }

    public Klient setImie(final String imie) {
        this.imie = imie;
        return this;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public Klient setNazwisko(final String nazwisko) {
        this.nazwisko = nazwisko;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Klient setEmail(final String email) {
        this.email = email;
        return this;
    }

    public String getUlica() {
        return ulica;
    }

    public Klient setUlica(final String ulica) {
        this.ulica = ulica;
        return this;
    }

    public String getNumer() {
        return numer;
    }

    public Klient setNumer(final String numer) {
        this.numer = numer;
        return this;
    }

    public String getKodPocztowy() {
        return kodPocztowy;
    }

    public Klient setKodPocztowy(final String kodPocztowy) {
        this.kodPocztowy = kodPocztowy;
        return this;
    }

    public String getMiasto() {
        return miasto;
    }

    public Klient setMiasto(final String miasto) {
        this.miasto = miasto;
        return this;
    }

    public List<Zamowienie> getZamowienia() {
        return zamowienia;
    }

    Klient setZamowienia(final List<Zamowienie> zamowienia) {
        this.zamowienia = zamowienia;
        return this;
    }
}