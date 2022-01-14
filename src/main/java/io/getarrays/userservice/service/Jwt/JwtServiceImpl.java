package io.getarrays.userservice.service.Jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import io.getarrays.userservice.domain.Role;
import io.getarrays.userservice.domain.User;
import io.getarrays.userservice.util.TokenType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.stream.Collectors;

@Service
public class JwtServiceImpl implements JwtService {

    /**
     * @param  user type is User Entity or User in security
     * @param  tokenType is type of token
     * */

    @Override
    public String generateToken(TokenType tokenType, Object user) {
        int expiresMinutes = 30;
        Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
        JWTCreator.Builder jwtCreator = JWT.create();
        if (tokenType == TokenType.ACCESS_TOKEN) {
            expiresMinutes = 10;
            if (user instanceof User) {
                User userEntity = (User) user;
                jwtCreator.withClaim("roles", userEntity.getRoles().stream().map(Role::getName).collect(Collectors.toList()));
            } else {
                org.springframework.security.core.userdetails.User userSecurity = (org.springframework.security.core.userdetails.User) user;
                jwtCreator.withSubject(userSecurity.getUsername())
                        .withClaim("roles", userSecurity.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()));
            }
        }
        jwtCreator.withExpiresAt(new Date(System.currentTimeMillis() + expiresMinutes * 60 * 1000));
        return jwtCreator.sign(algorithm);
    }
}


