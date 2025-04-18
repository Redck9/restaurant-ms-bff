package com.redck.restaurantmsbff.config;

import io.jsonwebtoken.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider
{
    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration}")
    private int jwtExpiration;

    @Value("${jwt.refreshExpiration}")
    private int refreshExpiration;

    public String generateToken(String userUid) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpiration * 1000L);

        return Jwts.builder().setSubject(userUid)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public String generateRefreshToken(String userUid) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + refreshExpiration * 1000L);

        return Jwts.builder().setSubject(userUid)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public String getTokenFromRequest(HttpServletRequest request)
    {
        String bearerToken = request.getHeader("Authorization");
        if(bearerToken != null && bearerToken.startsWith("Bearer "))
        {
            return bearerToken.substring(7);
        }
        return null;
    }

    public String getUserUidFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        }
        catch (ExpiredJwtException e) {
            System.out.println("Token expired: " + e.getMessage());
        }
        catch (MalformedJwtException e)
        {
            System.out.println("Invalid token: " + e.getMessage());
        }
        catch (Exception e) {
            System.out.println("Token validation error: " + e.getMessage());
        }
        return false;
    }
}
