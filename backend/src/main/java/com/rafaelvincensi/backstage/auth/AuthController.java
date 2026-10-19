package com.rafaelvincensi.backstage.auth;

import com.rafaelvincensi.backstage.token.TokenService;
import com.rafaelvincensi.backstage.user.User;
import com.rafaelvincensi.backstage.user.dto.LoginRequestDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public AuthController(AuthenticationManager authenticationManager, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginRequestDTO request){
        var token = new UsernamePasswordAuthenticationToken(request.email(), request.password());
        var authentication = authenticationManager.authenticate(token);

        User user = (User) authentication.getPrincipal();
        String jwtToken = tokenService.generateToken(user);

        return ResponseEntity.ok(Map.of("token", jwtToken));
    }
}
