package io.getarrays.userservice.service.Jwt;

import io.getarrays.userservice.domain.User;
import io.getarrays.userservice.util.TokenType;

public interface JwtService {
    String generateToken(TokenType tokenType, Object user);
}
