package com.nisum.exam.security;

import java.util.Date;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import com.nisum.exam.util.YamlConfig;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@EnableConfigurationProperties(YamlConfig.class)
@RequiredArgsConstructor
@Slf4j
public class JwtUtils {
  
	private final YamlConfig yamlConfig;
	    

    public boolean isTokenValid(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(yamlConfig.getJwtSecret())
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();

            return true;
        } catch (Exception e) {
            log.error("Token invalido");
            return false;
        }
    }

    public String generateToken(String email) { 
        return Jwts.builder()
                .setIssuedAt(new Date())
                .setSubject(email)
                .setExpiration(new Date(System.currentTimeMillis() + yamlConfig.getJwtExpiration()))
                .signWith(SignatureAlgorithm.HS512, yamlConfig.getJwtSecret())
                .compact();
        
    }
}
