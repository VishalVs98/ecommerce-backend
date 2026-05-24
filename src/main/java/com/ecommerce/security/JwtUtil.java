package com.ecommerce.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    private static final String SECRET =
            "mysecretkeymysecretkeymysecretkey12345";
    private final Key key =
            Keys.hmacShaKeyFor(
                    SECRET.getBytes());

    public String generateToken(
            String email){

        return Jwts.builder()

                .subject(email)

                .issuedAt(new Date())

                .expiration(
                        new Date(
                                System.currentTimeMillis()
                                        + 1000 * 60 * 60))

                .signWith(key)

                .compact();
    }


    public String extractEmail(
            String token){

        return Jwts.parser()

                .verifyWith((javax.crypto.SecretKey) key)

                .build()

                .parseSignedClaims(token)

                .getPayload()

                .getSubject();
    }


    public boolean isTokenValid(
            String token){
        try{
            Jwts.parser()
                    .verifyWith(
                            (javax.crypto.SecretKey) key)
                    .build()
                    .parseSignedClaims(token);

            return true;

        }catch(Exception e){

            return false;
        }
    }
}