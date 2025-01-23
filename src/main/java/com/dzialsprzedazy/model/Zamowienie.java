package com.dzialsprzedazy.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Zamowienie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "klient_id", nullable = false)
    private Klient klient;

    @ManyToOne
    @JoinColumn(name = "pracownik_id", nullable = false)
    private Pracownik pracownik;

    @Enumerated(EnumType.STRING)
    private Status status;

    private Date dataZalozenia;

    private Date terminRealizacji;

    private Double cenaLaczna;

    @OneToMany(mappedBy = "zamowienie", cascade = CascadeType.ALL)
    private List<ZamowienieTowar> towary = new ArrayList<>();

    public Zamowienie(final Long id, final Klient klient, final Pracownik pracownik, final Status status, final Date dataZalozenia, final Date terminRealizacji, final Double cenaLaczna, final List<ZamowienieTowar> towary) {
        this.id = id;
        this.klient = klient;
        this.pracownik = pracownik;
        this.status = status;
        this.dataZalozenia = dataZalozenia;
        this.terminRealizacji = terminRealizacji;
        this.cenaLaczna = cenaLaczna;
        this.towary = towary;
    }

    public Zamowienie() {

    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public Klient getKlient() {
        return klient;
    }

    public void setKlient(final Klient klient) {
        this.klient = klient;
    }

    public Pracownik getPracownik() {
        return pracownik;
    }

    public void setPracownik(final Pracownik pracownik) {
        this.pracownik = pracownik;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(final Status status) {
        this.status = status;
    }

    public Date getDataZalozenia() {
        return dataZalozenia;
    }

    public void setDataZalozenia(final Date dataZalozenia) {
        this.dataZalozenia = dataZalozenia;
    }

    public Date getTerminRealizacji() {
        return terminRealizacji;
    }

    public void setTerminRealizacji(final Date terminRealizacji) {
        this.terminRealizacji = terminRealizacji;
    }

    public Double getCenaLaczna() {
        return cenaLaczna;
    }

    public void setCenaLaczna(final Double cenaLaczna) {
        this.cenaLaczna = cenaLaczna;
    }

    public List<ZamowienieTowar> getTowary() {
        return towary;
    }

    public void setTowary(final List<ZamowienieTowar> towary) {
        this.towary = towary;
    }
}