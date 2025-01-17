package com.dzialsprzedazy.repository;

import com.dzialsprzedazy.model.Pracownik;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PracownikRepository extends JpaRepository<Pracownik, Long> {
    Page<Pracownik> findAll(Pageable pageable);
}
