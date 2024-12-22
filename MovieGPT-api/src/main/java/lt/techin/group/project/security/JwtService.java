package lt.techin.group.project.security;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lt.techin.group.project.rest.dto.UserDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String definitelyNotASecretKey;

    @Value("${jwt.expiration}")
    private long expiration;


    public Map<String, String> generateToken(UserDto user) {
        String token = Jwts.builder()
                .setClaims(Map.of(
                        "id", user.getId(),
                        "username", user.getUsername(),
                        "email", user.getEmail(),
                        "roles", user.getRoles()
                ))
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(SignatureAlgorithm.HS256, definitelyNotASecretKey)
                .compact();

        return Map.of("token", token);
    }

    public Claims getClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(definitelyNotASecretKey)
                .parseClaimsJws(token)
                .getBody();

    }

    public boolean isTokenValid(String token) {
        try {
            Claims claims = getClaimsFromToken(token);
            return !claims.getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    public String getUsernameFromToken(String token) {
        return getClaimsFromToken(token).get("username").toString();
    }
}
