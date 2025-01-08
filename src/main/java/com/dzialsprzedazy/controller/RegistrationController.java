package com.dzialsprzedazy.controller;

import com.dzialsprzedazy.model.dto.RegisterRequest;
import com.dzialsprzedazy.repository.UserRepository;
import com.dzialsprzedazy.model.*;
import com.dzialsprzedazy.repository.KlientRepository;
import com.dzialsprzedazy.repository.PracownikRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class RegistrationController {

    private final UserRepository userRepository;
    private final KlientRepository klientRepository;
    private final PracownikRepository pracownikRepository;
    private final PasswordEncoder passwordEncoder;

    public RegistrationController(UserRepository userRepository, KlientRepository klientRepository,
                                  PracownikRepository pracownikRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.klientRepository = klientRepository;
        this.pracownikRepository = pracownikRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public String registerUser(@RequestBody RegisterRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEmail(request.getEmail());
        user.setRole(Role.ROLE_USER);

        Klient klient = new Klient();
        klient.setUser(user);
        klientRepository.save(klient);

        userRepository.save(user);

        return "Zarejestrowano użytkownika jako klienta.";
    }

    @PostMapping("/register-admin")
    public String registerAdmin(@RequestBody RegisterRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEmail(request.getEmail());
        user.setRole(Role.ROLE_ADMIN);

        Pracownik pracownik = new Pracownik();
        pracownik.setUser(user);
        pracownik.setStanowisko(Stanowisko.Pracownik);

        pracownikRepository.save(pracownik);
        userRepository.save(user);

        return "Zarejestrowano pracownika jako admina.";
    }
}
