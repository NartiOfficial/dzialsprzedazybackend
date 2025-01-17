package com.dzialsprzedazy.repository;

import com.dzialsprzedazy.model.Zamowienie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZamowienieRepository extends JpaRepository<Zamowienie, Long> {
    Page<Zamowienie> findAll(Pageable pageable);
}
