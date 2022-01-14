package io.getarrays.userservice.service.auth;

import io.getarrays.userservice.service.Jwt.JwtService;
import io.getarrays.userservice.util.TokenType;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service @RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    @Override
    public  Map<String, String> userLogin(String username, String password) throws Exception {
        try {

         Authentication authentication =  authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
            User user = (User)authentication.getPrincipal();

            String access_token = jwtService.generateToken(TokenType.ACCESS_TOKEN,user);
            String refresh_token = jwtService.generateToken(TokenType.REFRESH_TOKEN,user);

            Map<String, String> tokens = new HashMap<>();
            tokens.put("access_token", access_token);
            tokens.put("refresh_token", refresh_token);
            return tokens;
        }catch (Exception error){
            throw new Exception("IErorr",error);
        }
    }
}
