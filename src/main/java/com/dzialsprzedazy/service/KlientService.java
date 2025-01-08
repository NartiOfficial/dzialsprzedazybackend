package com.dzialsprzedazy.service;

import com.dzialsprzedazy.model.Klient;
import com.dzialsprzedazy.repository.KlientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KlientService {

    private final KlientRepository klientRepository;

    public KlientService(KlientRepository klientRepository) {
        this.klientRepository = klientRepository;
    }

    public List<Klient> getAllKlienci() {
        return klientRepository.findAll();
    }

    public Klient getKlientById(Long id) {
        return klientRepository.findById(id).orElseThrow(() -> new RuntimeException("Klient not found"));
    }

    public Klient createKlient(Klient klient) {
        return klientRepository.save(klient);
    }
}
