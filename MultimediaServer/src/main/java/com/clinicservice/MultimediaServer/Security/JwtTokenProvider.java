package com.clinicservice.MultimediaServer.Security;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
@SuppressWarnings("deprecation")
public class JwtTokenProvider {
    private String jwtSecret = "4261656C64756E67anhsw2321312533211";
    Key secretKey = Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
  
    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secretKey).build().parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    public Authentication getAuthentication(String token) {
        Claims claims = Jwts.parser().setSigningKey(secretKey).build().parseSignedClaims(token).getPayload();
        String username = claims.getSubject();
        String authoritiesJson = claims.get("authorities", String.class);
        if (authoritiesJson == null) {
            // Manejar el caso en el que no se encuentren roles en los claims
            throw new IllegalStateException("No se encontraron roles en los claims del token.");
        }
        List<String> rolesList = Arrays.asList(authoritiesJson.split(","));
        Collection<? extends GrantedAuthority> authorities = rolesList.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
        return new UsernamePasswordAuthenticationToken(username, null, authorities);
    }
}
