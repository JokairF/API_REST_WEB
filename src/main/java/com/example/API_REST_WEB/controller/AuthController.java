package com.example.API_REST_WEB.controller;

import com.example.API_REST_WEB.dto.request.LoginRequest;
import com.example.API_REST_WEB.dto.request.SignupRequest;
import com.example.API_REST_WEB.dto.response.JwtResponse;
import com.example.API_REST_WEB.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(authService.authenticateUser(loginRequest));
    }

    @PostMapping("/signup")
    public ResponseEntity<String> register(@RequestBody SignupRequest signupRequest) {
        authService.registerUser(signupRequest);
        return ResponseEntity.ok("Utilisateur enregistré avec succès !");
    }
}
