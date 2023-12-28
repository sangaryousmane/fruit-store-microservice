package com.ousmane.authenticationservice.jwt;

import com.ousmane.authenticationservice.security.CustomUserDetailsService;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtUtils.class);

    private final CustomUserDetailsService userDetailsService;

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expireMs}")
    private int jwtExpirationMs;

    public String generateJwtToken(String username) {
        return generateTokenFromUsername(username);
    }

    public String generateTokenFromUsername(String username) {
        UserDetails userDetails = userDetailsService
                .loadUserByUsername(username);
        StringBuilder roles = new StringBuilder();
        userDetails.getAuthorities().forEach(auth -> {
            roles.append(auth.getAuthority()).append(" ");
        });
        return Jwts.builder()
                .setSubject(username)
                .setIssuer(roles.toString())
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public String getUsernameFromJwtToken(String token) {
        return Jwts.parser().setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        }
        catch (SignatureException se) {
            LOGGER.error("JwtUtils | validateJwtToken | Invalid JWT signature: {}", se.getMessage());
        } catch (MalformedJwtException e) {
        LOGGER.error("JwtUtils | validateJwtToken | Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
        LOGGER.error("JwtUtils | validateJwtToken | JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
        LOGGER.error("JwtUtils | validateJwtToken | JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
        LOGGER.error("JwtUtils | validateJwtToken | JWT claims string is empty: {}", e.getMessage());
        }
        return false;
    }

}
