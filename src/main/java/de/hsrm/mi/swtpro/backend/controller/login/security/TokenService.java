package de.hsrm.mi.swtpro.backend.controller.login.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.function.Function;

/**
 * The TokenService is responsible for performing JWT
 * operations 
 */
@Component
public class TokenService {
    private String secret;

    @Autowired
    public TokenService(@Value("${security.jwt.token.secret:secret-key}") String secret) {
        this.secret = secret;
    }

    /**
     * retrieve username from jwt token
     * @param token
     * @return username
     */
    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    /**
     * retrieve expiration date from jwt token
     * @param token
     * @return expiration date
     */
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    /**
     * retrieve a information from jwt token
     * @param <T>
     * @param token
     * @param claimsResolver information from token
     * @return
     */
    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    /**
     * for retrieveing any information 
     * from token we need the secret key
     * @param token
     * @return any information from token
     */
    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }
    /**
     * check if the token has expired
     * @param token
     * @return boolean if the token has expired
     */
    private Boolean isTokenNotExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.after(new Date());
    }

    /**
     * validates the token
     * @param token
     * @return Optional<Boolean>
     */
    public Optional<Boolean> validateToken(String token) {
        return  isTokenNotExpired(token) ? Optional.of(Boolean.TRUE) : Optional.empty();
    }

    /**
     * returns token of logged in user
     * @param request
     * @return
     */

    @Value("${security.jwt.token.header:Authorization}")
    private String tokenHeader;

    public String getToken(HttpServletRequest request) {
        final String requestHeader = request.getHeader(this.tokenHeader);
        String authenticationToken = null;

        if (requestHeader != null && requestHeader.startsWith("Bearer ")) {
            authenticationToken = requestHeader.substring(7);
            JwtAuthentication authentication = new JwtAuthentication(authenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        return authenticationToken;
    }

    /**
     * returns username of logged in user
     * @param request
     * @return
     */
    public String getUsernameFromRequest(HttpServletRequest request) {
        return getUsernameFromToken(getToken(request));
    }
}
