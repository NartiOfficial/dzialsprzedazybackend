package com.dzialsprzedazy.repository;

import com.dzialsprzedazy.model.Klient;
import com.dzialsprzedazy.model.Role;
import com.dzialsprzedazy.model.Stanowisko;
import com.dzialsprzedazy.model.Status;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface KlientRepository extends CrudRepository<Klient, Long> {
    Klient save(Klient klient);
    Klient findByUsername(String username);
    Klient findByEmail(String email);
    Klient findByUsernameAndPassword(String username, String password);
    Klient findByEmailAndPassword(String email, String password);
    Optional<Klient> findById(Long id);
    void delete(Klient klient);
    List<Klient> findAll();
    List<Klient> findByRole(Role role);
    List<Klient> findByStanowisko(Stanowisko stanowisko);
    List<Klient> findByStatus(Status status);
    List<Klient> findByRoleAndStanowisko(Role role, Stanowisko stanowisko);
    List<Klient> findByRoleAndStatus(Role role, Status status);
    List<Klient> findByStanowiskoAndStatus(Stanowisko stanowisko, Status status);
}
