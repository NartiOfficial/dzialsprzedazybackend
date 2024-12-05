package com.dzialsprzedazy.repository;

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

import java.util.Date;
import java.util.List;

@Entity
class Zamowienie {
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
    private List<ZamowienieTowar> towary;
}