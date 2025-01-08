package com.dzialsprzedazy.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
class Towar {
    @Id
    private Long id;

    @OneToMany(mappedBy = "towar", cascade = CascadeType.ALL)
    private List<ZamowienieTowar> zamowienieTowar;

    private String nazwaProduktu;

    private Double cenaJednostkowa;

    private String jednostkaMiara;

    private Double stanMagazynowy;

    private String opis;
}