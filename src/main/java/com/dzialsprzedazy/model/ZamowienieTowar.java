package com.dzialsprzedazy.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class ZamowienieTowar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "zamowienie_id", nullable = false)
    private Zamowienie zamowienie;

    @ManyToOne
    @JoinColumn(name = "towar_id", nullable = false)
    private Towar towar;

    private Double ilosc;

    private Double cenaSprzedazy;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public Zamowienie getZamowienie() {
        return zamowienie;
    }

    public void setZamowienie(final Zamowienie zamowienie) {
        this.zamowienie = zamowienie;
    }

    public Towar getTowar() {
        return towar;
    }

    public void setTowar(final Towar towar) {
        this.towar = towar;
    }

    public Double getIlosc() {
        return ilosc;
    }

    public void setIlosc(final Double ilosc) {
        this.ilosc = ilosc;
    }

    public Double getCenaSprzedazy() {
        return cenaSprzedazy;
    }

    public void setCenaSprzedazy(final Double cenaSprzedazy) {
        this.cenaSprzedazy = cenaSprzedazy;
    }
}