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
        return klientRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Klient not found"));
    }

    public Klient createKlient(Klient klient) {
        return klientRepository.save(klient);
    }

    public Klient updateKlient(Long id, Klient klient) {
        Klient existingKlient = getKlientById(id);
        existingKlient.setImie(klient.getImie());
        existingKlient.setNazwisko(klient.getNazwisko());
        existingKlient.setEmail(klient.getEmail());
        existingKlient.setUlica(klient.getUlica());
        existingKlient.setNumer(klient.getNumer());
        existingKlient.setKodPocztowy(klient.getKodPocztowy());
        existingKlient.setMiasto(klient.getMiasto());
        return klientRepository.save(existingKlient);
    }

    public void deleteKlient(Long id) {
        klientRepository.deleteById(id);
    }
}