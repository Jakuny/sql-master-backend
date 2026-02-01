package com.jakunya.sqlmaster.security.jwt;

import com.jakunya.sqlmaster.dto.JwtAuthDto;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Component
public class JwtService {

    @Value("${jwt.secret}")
    private String jwtSecret;
    @Value("${jwt.access-token-expiration-minutes}")
    private long accessTokenExpirationMinutes;
    @Value("${jwt.refresh-token-expiration-days}")
    private long refreshTokenExpirationDays;

    private static final Logger LOGGER = LogManager.getLogger(JwtService.class);

    public JwtAuthDto genJwtAuthToken(String email){
        JwtAuthDto jwtdto = new JwtAuthDto();
        jwtdto.setToken(genJwtToken(email));
        jwtdto.setRefreshToken(genRefreshToken(email));
        return jwtdto;
    }

    // Метод для ОБНОВЛЕНИЯ (Refresh)
    public JwtAuthDto refreshBaseToken(String email, String refreshToken){
        JwtAuthDto jwtdto = new JwtAuthDto();
        jwtdto.setToken(genJwtToken(email));
        jwtdto.setRefreshToken(refreshToken);
        return jwtdto;
    }

    public boolean validateJwtToken(String token){
        try {
            Jwts.parser()
                    .verifyWith(getSingInKey())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
            return true;
        } catch (ExpiredJwtException expEx){
            LOGGER.error("Expired JwtException ", expEx);
        } catch (UnsupportedJwtException expEx){
            LOGGER.error("Unsupported JwtException ", expEx);
        } catch (MalformedJwtException expEx){
            LOGGER.error("Malformed JwtException ", expEx);
        } catch (SecurityException expEx) {
            LOGGER.error("Security Exception ", expEx);
        } catch (Exception expEx){
            LOGGER.error("Invalid Token ", expEx);
        }
        return false;
    }

    public String getEmailFromToken(String token){
        Claims claims = Jwts.parser()
                .verifyWith(getSingInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
        return claims.getSubject();
    }

    private String genJwtToken(String email){
        java.util.Date date = Date.from(LocalDateTime.now().plusMinutes(accessTokenExpirationMinutes).atZone(ZoneId.systemDefault()).toInstant());
        return Jwts.builder()
                .subject(email)
                .expiration(date)
                .signWith(getSingInKey())
                .compact();
    }

    private String genRefreshToken(String email){
        java.util.Date date = Date.from(LocalDateTime.now().plusDays(refreshTokenExpirationDays).atZone(ZoneId.systemDefault()).toInstant());
        return Jwts.builder()
                .subject(email)
                .expiration(date)
                .signWith(getSingInKey())
                .compact();
    }

    private SecretKey getSingInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSecret);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
