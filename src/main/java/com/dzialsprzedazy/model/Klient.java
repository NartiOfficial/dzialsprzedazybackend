package com.dzialsprzedazy.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;

import java.util.List;

@Entity
public class Klient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private String imie;

    private String nazwisko;

    private String email;

    private String ulica;

    private String numer;

    private String kodPocztowy;

    private String miasto;

    @OneToMany(mappedBy = "klient")
    private List<Zamowienie> zamowienia;

    Long getId() {
        return id;
    }

    Klient setId(final Long id) {
        this.id = id;
        return this;
    }

    User getUser() {
        return user;
    }

    Klient setUser(final User user) {
        this.user = user;
        return this;
    }

    String getImie() {
        return imie;
    }

    Klient setImie(final String imie) {
        this.imie = imie;
        return this;
    }

    String getNazwisko() {
        return nazwisko;
    }

    Klient setNazwisko(final String nazwisko) {
        this.nazwisko = nazwisko;
        return this;
    }

    String getEmail() {
        return email;
    }

    Klient setEmail(final String email) {
        this.email = email;
        return this;
    }

    String getUlica() {
        return ulica;
    }

    Klient setUlica(final String ulica) {
        this.ulica = ulica;
        return this;
    }

    String getNumer() {
        return numer;
    }

    Klient setNumer(final String numer) {
        this.numer = numer;
        return this;
    }

    String getKodPocztowy() {
        return kodPocztowy;
    }

    Klient setKodPocztowy(final String kodPocztowy) {
        this.kodPocztowy = kodPocztowy;
        return this;
    }

    String getMiasto() {
        return miasto;
    }

    Klient setMiasto(final String miasto) {
        this.miasto = miasto;
        return this;
    }

    List<Zamowienie> getZamowienia() {
        return zamowienia;
    }

    Klient setZamowienia(final List<Zamowienie> zamowienia) {
        this.zamowienia = zamowienia;
        return this;
    }
}