package pl.meklad.ipezput2k20.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import pl.meklad.ipezput2k20.model.domain.User;

import java.util.Arrays;
import java.util.Date;
import java.util.function.Function;

/**
 * Create by dev on 06.11.2020
 */
@Component
public class JwtTokenUtil {
    // czas użycia tokena czyli 24h * 60m * 60s * 1000ms
    public static final long ACCESS_TOKEN_VALIDITY_SECONDS = 2 * 60 * 60 * 1000;

    // klucz szyfracji i deszyfracji tokenu zawarty w trzeciej częsci tokenu
    public static final String SIGNING_KEY = "sdasd"; //to_jest naprawdę naprawdę strasznie ciężki do złamania klucz szyfrujący :D

    // metoda wyciągająca username z tokenu
    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    // wyciągnięcie daty ważności tokenu
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(SIGNING_KEY)
                .parseClaimsJws(token)
                .getBody();
    }

    // sprawdzanie czy token stracił ważność
    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    // generowanie tokenu
    public String generateToken(User user) {
        return doGenerateToken(user.getUsername(), user.getUserRole().toString());
    }

    // generowanie tokenu na podstawie nazwy
    private String doGenerateToken(String subject, String role) {

        Claims claims = Jwts
                .claims()
                .setSubject(subject);
        claims.put("scopes", Arrays.asList(new SimpleGrantedAuthority(role)));

        return Jwts
                .builder()
                .setClaims(claims)
                // podpis serwera
                .setIssuer("http://youtube.com")
                // zapisanie daty
                .setIssuedAt(new Date(System.currentTimeMillis()))
                // ustawienie długości życia tokenu od teraz do ACCESS_TOKEN_VALIDITY_SECONDS
                .setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_VALIDITY_SECONDS))
                // z podpisem szyfrowania
                .signWith(SignatureAlgorithm.HS256, SIGNING_KEY)
                .compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
