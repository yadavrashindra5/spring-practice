package com.practice.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtHelper {

    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;

//    @Value("${SECRET_KEY}")
    public String SECRET_KEY = "gfhbjkljhgvbbbjdbfshfsjbfhbsghjfhshfhjsbfhskkkkkkkkkkkkkkkkkkkkkkkkkkkkkjkkkkkkkkkkkkkkkkkjhfsdffffffffffsdsdfsdadsfsfsafsfd";

    public String getUserNameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    private <R> R getClaimFromToken(String token, Function<Claims, R> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        SecretKey secretKey = getSecretKey();
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload();
    }

    public Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return doGenerateToken(claims, userDetails.getUsername());
    }

    private String doGenerateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder().claims().add(claims).subject(subject).issuedAt(new Date(System.currentTimeMillis())).expiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY)).and().signWith(getSecretKey()).compact();
    }

    private SecretKey getSecretKey() {
        byte[] bytes = Decoders.BASE64.decode(SECRET_KEY);
        SecretKey key = Keys.hmacShaKeyFor(bytes);
        return key;
    }
}
