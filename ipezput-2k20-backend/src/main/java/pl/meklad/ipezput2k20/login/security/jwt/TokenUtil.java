package pl.meklad.ipezput2k20.login.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import pl.meklad.ipezput2k20.login.domain.Users;
import pl.meklad.ipezput2k20.login.enums.UserRole;
import pl.meklad.ipezput2k20.login.security.authentication.UserAuth;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Optional;

/**
 * Create by dev on 24.10.2020
 */
@Service
public class TokenUtil {
    private static final long VALIDITY_TIME_MS = 2 * 60 * 60 * 1000; // 2 hours  validity
    private static final String AUTH_HEADER_NAME = "Authorization";

    private final String SECRET = "Very, very, very private and protected secret key as long as this very bad sentence is.";

    public Optional<Authentication> verifyToken(HttpServletRequest request) {
        final String token = request.getHeader(AUTH_HEADER_NAME);

        if (token != null && !token.isEmpty()) {
            final UserToken user = parseUserFromToken(token.replace("Bearer", "").trim());
            if (user != null) {
                return Optional.of(new UserAuth(user));
            }
        }
        return Optional.empty();
    }

    public String createTokenForUser(UserToken token) {
        return createTokenForUser(token.getUser());
    }

    public String createTokenForUser(Users user) {
        return Jwts.builder()
                .setExpiration(new Date(System.currentTimeMillis() + VALIDITY_TIME_MS))
                .setSubject(user.getUserName())
                .claim("userId", user.getUserId())
                .claim("role", user.getUserRole().toString())
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();
    }

    public UserToken parseUserFromToken(String token) {

        Claims claims = Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody();

        Users user = new Users();
        user.setUserId(Long.valueOf(claims.getId()));
        user.setUserRole(UserRole.valueOf((String) claims.get("role")));
        if (user.getUserId() != null && user.getUserRole() != null) {
            return new UserToken(user);
        } else {
            return null;
        }
    }
}
