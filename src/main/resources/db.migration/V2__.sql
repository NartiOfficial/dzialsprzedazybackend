-- Insert sample data into klient table
INSERT INTO klient (imie, nazwisko, email, ulica, numer, kod_pocztowy, miasto) VALUES
('Jan', 'Kowalski', 'jan.kowalski@test.com', 'Kwiatowa', '10A', '00-001', 'Warszawa'),
('Anna', 'Nowak', 'anna.nowak@test.com', 'Różana', '15B', '00-002', 'Kraków'),
('Piotr', 'Wiśniewski', 'piotr.wisniewski@test.com', 'Słoneczna', '8C', '00-003', 'Poznań'),
('Ewa', 'Zielińska', 'ewa.zielinska@test.com', 'Wiosenna', '20D', '00-004', 'Wrocław'),
('Tomasz', 'Lewandowski', 'tomasz.lewandowski@test.com', 'Jesienna', '25E', '00-005', 'Gdańsk');

-- Insert sample data into pracownik table
INSERT INTO pracownik (imie, nazwisko, stanowisko, numer_telefonu, email) VALUES
                                                                              ('Adam', 'Nowicki', 'Kierownik', '123456789', 'adam.nowicki@test.com'),
                                                                              ('Barbara', 'Kaczmarek', 'Sprzedawca', '987654321', 'barbara.kaczmarek@test.com'),
                                                                              ('Cezary', 'Kowalczyk', 'Magazynier', '456123789', 'cezary.kowalczyk@test.com'),
                                                                              ('Danuta', 'Mazur', 'Sprzedawca', '789456123', 'danuta.mazur@test.com'),
                                                                              ('Edward', 'Krawczyk', 'Specjalista', '321654987', 'edward.krawczyk@test.com');

-- Insert sample data into towar table
INSERT INTO towar (id, nazwa_produktu, cena_jednostkowa, jednostka_miara, stan_magazynowy, opis) VALUES
                                                                                                     (1, 'Młotek', 25.50, 'sztuka', 100, 'Młotek uniwersalny'),
                                                                                                     (2, 'Wkrętarka', 150.00, 'sztuka', 50, 'Wkrętarka akumulatorowa'),
                                                                                                     (3, 'Farba biała', 50.00, 'litr', 80, 'Farba do ścian'),
                                                                                                     (4, 'Taśma klejąca', 5.00, 'sztuka', 500, 'Taśma uniwersalna'),
                                                                                                     (5, 'Śrubokręt', 20.00, 'sztuka', 150, 'Śrubokręt krzyżowy');

-- Insert sample data into zamowienie table
INSERT INTO zamowienie (klient_id, pracownik_id, status, data_zalozenia, termin_realizacji, cena_laczna) VALUES
                                                                                                             (1, 1, 'W realizacji', '2025-01-01 10:00:00', '2025-01-07 10:00:00', 100.00),
                                                                                                             (2, 2, 'Zrealizowane', '2025-01-02 11:00:00', '2025-01-08 11:00:00', 200.00),
                                                                                                             (3, 3, 'Anulowane', '2025-01-03 12:00:00', '2025-01-09 12:00:00', 300.00),
                                                                                                             (4, 4, 'W realizacji', '2025-01-04 13:00:00', '2025-01-10 13:00:00', 400.00),
                                                                                                             (5, 5, 'Zrealizowane', '2025-01-05 14:00:00', '2025-01-11 14:00:00', 500.00);

-- Insert sample data into zamowienie_towar table
INSERT INTO zamowienie_towar (zamowienie_id, towar_id, ilosc, cena_sprzedazy) VALUES
                                                                                  (1, 1, 2, 51.00),
                                                                                  (1, 2, 1, 150.00),
                                                                                  (2, 3, 3, 150.00),
                                                                                  (3, 4, 10, 50.00),
                                                                                  (4, 5, 5, 100.00);
