package com.dzialsprzedazy.repository;

import com.dzialsprzedazy.model.Towar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TowarRepository extends JpaRepository<Towar, Long> {
}
