package com.dzialsprzedazy.repository;

import com.dzialsprzedazy.model.Pracownik;
import org.springframework.data.repository.CrudRepository;

public interface PracownikRepository extends CrudRepository<Pracownik, Long> {
    Pracownik findByEmail(String email);
    Pracownik findByLogin(String login);
    Pracownik findByPesel(String pesel);
    Pracownik findByNrTelefonu(String nrTelefonu);
    Pracownik findByNrDowodu(String nrDowodu);
    Pracownik findByNrKonta(String nrKonta);
}
