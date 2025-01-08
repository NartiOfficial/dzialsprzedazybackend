package com.dzialsprzedazy.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Pracownik {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private String imie;

    private String nazwisko;

    @Enumerated(EnumType.STRING)
    private Stanowisko stanowisko;

    private String numerTelefonu;

    private String email;

    @OneToMany(mappedBy = "pracownik")
    private List<Zamowienie> zamowienia;
}