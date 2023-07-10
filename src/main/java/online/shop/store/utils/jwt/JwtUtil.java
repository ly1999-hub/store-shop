package online.shop.store.utils.jwt;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtUtil {
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long jwtExpiration;

    public Claims extractAllClaims(String token) {return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();}

    public Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }
    
    // ExtractUserName from Token
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // GenerateToken with email and roles...
    public String generateToken(String email , List<String> roles) {

        return Jwts.builder()
                    .setSubject(email)
                    .claim("role",roles)
                    .setIssuedAt(new Date(System.currentTimeMillis()))
                    .setExpiration(Date.from(Instant.now()
                    .plus(jwtExpiration, ChronoUnit.MILLIS)))
                    .signWith(SignatureAlgorithm.HS256, secret)
                    .compact();
    }

    // validate token 
    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token);
            return true;
        } catch (SignatureException e) {
            log.info("Invalid JWT signature.");
            log.trace("Invalid JWT signature trace: {}", e);
        } catch (MalformedJwtException e) {
            log.info("Invalid JWT token.");
            log.trace("Invalid JWT token trace: {}", e);
        } catch (ExpiredJwtException e) {
            log.info("Expired JWT token.");
            log.trace("Expired JWT token trace: {}", e);
        } catch (UnsupportedJwtException e) {
            log.info("Unsupported JWT token.");
            log.trace("Unsupported JWT token trace: {}", e);
        } catch (IllegalArgumentException e) {
            log.info("JWT token compact of handler are invalid.");
            log.trace("JWT token compact of handler are invalid trace: {}", e);
        }
        return false;
    }

    public String getToken(HttpServletRequest httpServletRequest){
        final String bearerToken =httpServletRequest.getHeader("Authorization");
        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer "))
            {return bearerToken.substring(7,bearerToken.length()); } // The part after "Bearer "
        return null;
    }

    public Date extractExpiration(String token) { return extractClaim(token, Claims::getExpiration); }
    
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

}
