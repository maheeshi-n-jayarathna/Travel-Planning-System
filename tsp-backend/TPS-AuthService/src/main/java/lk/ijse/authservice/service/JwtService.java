package lk.ijse.authservice.service;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.function.Function;


public interface JwtService {
    void validateToken(String token);

    String generateToken(String userName);

    String extractUsername(String token);

    <T> T extractClaim(String token, Function<Claims, T> claimResolover);

    Boolean validateToken(String token, UserDetails userDetails);
}
