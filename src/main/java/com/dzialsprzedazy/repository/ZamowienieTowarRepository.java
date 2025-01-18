package com.dzialsprzedazy.repository;

import com.dzialsprzedazy.model.ZamowienieTowar;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZamowienieTowarRepository extends JpaRepository<ZamowienieTowar, Long> {
    Page<ZamowienieTowar> findAll(Pageable pageable);

    Page<ZamowienieTowar> findByZamowienieId(Long zamowienieId, Pageable pageable);
}
