package com.dzialsprzedazy.repository;

import com.dzialsprzedazy.model.Zamowienie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ZamowienieRepository extends JpaRepository<Zamowienie, Long> {
}
