package com.dzialsprzedazy.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class Towar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "towar", cascade = CascadeType.ALL)
    private List<ZamowienieTowar> zamowienieTowar;

    private String nazwaProduktu;

    private Double cenaJednostkowa;

    private String jednostkaMiara;

    private Double stanMagazynowy;

    private String opis;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public List<ZamowienieTowar> getZamowienieTowar() {
        return zamowienieTowar;
    }

    public void setZamowienieTowar(final List<ZamowienieTowar> zamowienieTowar) {
        this.zamowienieTowar = zamowienieTowar;
    }

    public String getNazwaProduktu() {
        return nazwaProduktu;
    }

    public void setNazwaProduktu(final String nazwaProduktu) {
        this.nazwaProduktu = nazwaProduktu;
    }

    public Double getCenaJednostkowa() {
        return cenaJednostkowa;
    }

    public void setCenaJednostkowa(final Double cenaJednostkowa) {
        this.cenaJednostkowa = cenaJednostkowa;
    }

    public String getJednostkaMiara() {
        return jednostkaMiara;
    }

    public void setJednostkaMiara(final String jednostkaMiara) {
        this.jednostkaMiara = jednostkaMiara;
    }

    public Double getStanMagazynowy() {
        return stanMagazynowy;
    }

    public void setStanMagazynowy(final Double stanMagazynowy) {
        this.stanMagazynowy = stanMagazynowy;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(final String opis) {
        this.opis = opis;
    }
}