package io.getarrays.userservice.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Get Arrays (https://www.getarrays.io/)
 * @version 1.0
 * @since 7/10/2021
 */
@Slf4j
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;
    public CustomAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        log.info("Username is: {}", username); log.info("Password is: {}", password);
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        return authenticationManager.authenticate(authenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
//        User user = (User)authentication.getPrincipal();
//        Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
//
//        String access_token = jwtService.generateToken(TokenType.ACCESS_TOKEN,user,10,request.getRequestURL().toString());
//
//        String refresh_token = jwtService.generateToken(TokenType.REFRESH_TOKEN,user,30,null);
//
//        Map<String, String> tokens = new HashMap<>();
//        tokens.put("access_token", access_token);
//        tokens.put("refresh_token", refresh_token);
//        response.setContentType(APPLICATION_JSON_VALUE);
//        new ObjectMapper().writeValue(response.getOutputStream(), tokens);
    }
}
