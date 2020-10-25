package ru.geekbrains.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.dto.JwtRequestToken;
import ru.geekbrains.dto.JwtResponseToken;
import ru.geekbrains.jwt.JwtTokenProvider;
import ru.geekbrains.service.UserDetailsServiceImplements;

@RequestMapping("/auth")
@RestController
@AllArgsConstructor
public class AuthController {

    private final UserDetailsServiceImplements userDetailsServiceImplements;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody JwtRequestToken jwtRequestToken){
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(jwtRequestToken.getLogin(), jwtRequestToken.getPassword()));
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(403).body("неверный логин или пароль");
        }
        UserDetails userDetails = userDetailsServiceImplements.loadUserByUsername(jwtRequestToken.getLogin());
        JwtResponseToken jwtResponseToken = new JwtResponseToken(jwtTokenProvider.generateToken(userDetails));
        return ResponseEntity.ok(jwtResponseToken.getToken());
    }

}
