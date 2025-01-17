package com.dzialsprzedazy.repository;

import com.dzialsprzedazy.model.Klient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KlientRepository extends JpaRepository<Klient, Long> {
    Page<Klient> findAll(Pageable pageable);
}
