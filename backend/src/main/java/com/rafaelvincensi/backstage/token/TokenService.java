package com.rafaelvincensi.backstage.token;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.rafaelvincensi.backstage.user.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.time.Instant;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expirationTime;

    public String generateToken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            return JWT.create()
                    .withIssuer("backstage-api")
                    .withSubject(user.getEmail())
                    .withExpiresAt(Instant.now().plusMillis(expirationTime))
                    .sign(algorithm);
        } catch (JWTCreationException ex) {
            throw new RuntimeException("Error generating token", ex);
        }
}

}
