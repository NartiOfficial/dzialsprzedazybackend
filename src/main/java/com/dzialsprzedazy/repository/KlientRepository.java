package com.dzialsprzedazy.repository;

import com.dzialsprzedazy.model.Klient;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface KlientRepository extends CrudRepository<Klient, Long> {

    @Query("select k from Klient k where k.username = ?1")
    Optional<Klient> findByUsername(String username);

    @Override
    List<Klient> findAll();
}
