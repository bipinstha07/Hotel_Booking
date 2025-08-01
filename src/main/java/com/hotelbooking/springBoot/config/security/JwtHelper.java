package com.hotelbooking.springBoot.config.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.security.core.userdetails.UserDetails;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

public class JwtHelper {

    public static final int validity = 60 *  60 * 1000; // 1 hr validate
    private final String secret_key = "fdf";

    private Key key;

    @PostConstruct
    public void init(){
        this.key= Keys.hmacShaKeyFor(secret_key.getBytes(StandardCharsets.UTF_8));
    }


    public String generateToken(UserDetails userDetails){
        return Jwts.builder()
                .subject(userDetails.getUsername())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis()+ validity))
                .signWith(key)
                .compact();
    }





}
