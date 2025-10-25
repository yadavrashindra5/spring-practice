package com.practice.security.jwt;

import com.practice.entities.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtHelper {
        public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60 * 1000;
    @Value("${jwt.secretKey}")
    private String SECRET_KEY;

    public String getUserNameFromToken(String token) {
        return getClaimsFromToken(token, Claims::getSubject);
    }

    public <T> T getClaimsFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        SecretKey key = getSigningKey();
        Claims payload = Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload();
        return payload;
    }

    public Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimsFromToken(token, Claims::getExpiration);
    }


    public String generateToken(User userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return doGenerateToken(claims, userDetails.getPhoneNumber());
    }

    private String doGenerateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder().claims(claims).subject(subject).issuedAt(new Date(System.currentTimeMillis())).expiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY)).signWith(getSigningKey()).compact();
    }

    private SecretKey getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
