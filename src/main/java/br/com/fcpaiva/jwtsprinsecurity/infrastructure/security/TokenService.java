package br.com.fcpaiva.jwtsprinsecurity.infrastructure.security;

import br.com.fcpaiva.jwtsprinsecurity.infrastructure.entity.UsuariosEntity;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;

@Service
public class TokenService {

    @Value("${default.secretkey}")
    private String secretKey;

    public String generateToken(UsuariosEntity usuariosEntity) {
        try{
            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            String token = JWT.create()
                    .withIssuer("auth-api")
                    .withSubject(usuariosEntity.getUsername())
                    .withExpiresAt(this.getExpirationDate())
                    .sign(algorithm);
            return token;
        }catch (JWTCreationException e){
            throw new RuntimeException(String.format("Error while generation token %s",e.getMessage()));
        }
    }

    public String validateToken(String token) {
        try{
            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            return JWT.require(algorithm)
                    .withIssuer("auth-api")
                    .build()
                    .verify(token)
                    .getSubject();
        }catch (JWTVerificationException e){
            return "";
        }
    }

    private Instant getExpirationDate() {
        return Instant.now().plusSeconds(7200L);
    }
}
