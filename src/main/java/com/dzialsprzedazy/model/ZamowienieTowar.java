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
}