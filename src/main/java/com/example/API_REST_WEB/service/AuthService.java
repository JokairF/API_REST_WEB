package com.example.API_REST_WEB.service;

import com.example.API_REST_WEB.dto.request.LoginRequest;
import com.example.API_REST_WEB.dto.request.SignupRequest;
import com.example.API_REST_WEB.dto.response.JwtResponse;
import com.example.API_REST_WEB.entity.Role;
import com.example.API_REST_WEB.entity.User;
import com.example.API_REST_WEB.repository.RoleRepository;
import com.example.API_REST_WEB.repository.UserRepository;
import com.example.API_REST_WEB.security.jwt.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    /**
     * Authentifie un utilisateur et retourne un JWT.
     */
    public JwtResponse authenticateUser(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        String jwt = jwtUtils.generateJwtToken(authentication);

        Set<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority) // Convertir les rôles en chaîne de caractères
                .collect(Collectors.toSet());

        return new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(), new ArrayList<>(roles));    }


    /**
     * Inscrit un nouvel utilisateur.
     */
    public void registerUser(SignupRequest signupRequest) {
        if (userRepository.existsByUsername(signupRequest.getUsername())) {
            throw new IllegalArgumentException("Erreur : Ce nom d'utilisateur est déjà pris !");
        }

        if (userRepository.existsByEmail(signupRequest.getEmail())) {
            throw new IllegalArgumentException("Erreur : Cet email est déjà utilisé !");
        }

        User user = new User();
        user.setUsername(signupRequest.getUsername());
        user.setEmail(signupRequest.getEmail());
        user.setPassword(passwordEncoder.encode(signupRequest.getPassword()));

        Set<Role> roles = new HashSet<>();
        if (signupRequest.getRoles() == null || signupRequest.getRoles().isEmpty()) {
            // Si aucun rôle n'est spécifié, on assigne le rôle "USER" par défaut
            Role defaultRole = roleRepository.findByName(Role.ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Erreur : Rôle USER introuvable."));
            roles.add(defaultRole);
        } else {
            for (String roleName : signupRequest.getRoles()) {
                Role role = roleRepository.findByName(Role.ERole.valueOf(roleName))
                        .orElseThrow(() -> new RuntimeException("Erreur : Rôle " + roleName + " introuvable."));
                roles.add(role);
            }
        }
        user.setRoles(roles);

        userRepository.save(user);
    }
}
