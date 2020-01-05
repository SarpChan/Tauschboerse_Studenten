package de.hsrm.mi.swtpro.backend.controller.login;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;

@Component
public class JwtTokenService {

    private String secret;
    private Long expiration;

    public JwtTokenService(@Value("${security.jwt.token.secret:secret-key}") String secret, @Value("${security.jwt.token.expiration:3600000}") Long expiration) {
        this.secret = secret;
        this.expiration = expiration;
    }

    public String generateToken(String username) {
        final Date createdDate = new Date();
        final Date expirationDate = calculateExpirationDate(createdDate);

        return Jwts.builder()
                .setClaims(new HashMap<>())
                .setSubject(username)
                .setIssuedAt(createdDate)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    private Date calculateExpirationDate(Date createdDate) {
        return new Date(createdDate.getTime() + expiration * 10000);
    }
}
