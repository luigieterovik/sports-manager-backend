package com.example.sportsmanager.controller;

import com.example.sportsmanager.dto.LoginRequestDTO;
import com.example.sportsmanager.dto.ResponseDTO;
import com.example.sportsmanager.model.User;
import com.example.sportsmanager.repository.UserRepository;
import com.example.sportsmanager.infra.security.TokenService;
import com.example.sportsmanager.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.authentication.AuthenticationManager;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;
    private final UserService userService;
    private AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        try {
            if (userService.emailExists(user.getEmail())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("O email já está em uso.");
            }

            userService.saveUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body("Usuário registrado com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao registrar usuário: " + e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequestDTO body) {
        User user = this.repository.findByEmail(body.email()).orElseThrow(() -> new RuntimeException("User not found"));
        if(passwordEncoder.matches(body.password(), user.getPassword())) {
            String token = this.tokenService.generateToken(user);
            return ResponseEntity.ok(new ResponseDTO(user.getName(), token));
        }
        return ResponseEntity.badRequest().build();
    }
}
