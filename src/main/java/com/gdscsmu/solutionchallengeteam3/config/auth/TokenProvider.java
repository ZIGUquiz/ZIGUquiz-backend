package com.gdscsmu.solutionchallengeteam3.config.auth;

import com.gdscsmu.solutionchallengeteam3.domain.auth.PrincipalDetails;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.SignatureException;
import java.util.Date;
import java.security.Key;

@Component
public class TokenProvider {

    private static final Logger logger = LoggerFactory.getLogger(TokenProvider.class);

    private Long tokenExpirationMsec;
    private Key tokenSecretKey;

    public TokenProvider(AppProperties appProperties) {
        this.tokenSecretKey = encodeTokenSecret(appProperties.getAuth().getTokenSecret());
        this.tokenExpirationMsec = appProperties.getAuth().getTokenExpirationMsec();
    }

    public String createToken(Authentication authentication) {
        PrincipalDetails userPrincipal = (PrincipalDetails) authentication.getPrincipal();

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + tokenExpirationMsec);

        return Jwts.builder()
                .setSubject(userPrincipal.getProviderId())
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(tokenSecretKey, SignatureAlgorithm.HS512)
                .compact();
    }

    public Long getUserIdFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(tokenSecretKey)
                .parseClaimsJws(token)
                .getBody();

        return Long.parseLong(claims.getSubject());
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parser()
                    .setSigningKey(tokenSecretKey)
                    .parseClaimsJws(authToken);
            return true;
//        } catch (SignatureException ex) {
//            logger.error("Invalid JWT signature");
        } catch (MalformedJwtException ex) {
            logger.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            logger.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            logger.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            logger.error("JWT claims string is empty.");
        }
        return false;
    }

    private Key encodeTokenSecret(String tokenSecret) {
        return Keys.hmacShaKeyFor(tokenSecret.getBytes());
    }
}
