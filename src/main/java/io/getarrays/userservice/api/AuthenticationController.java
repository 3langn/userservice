package io.getarrays.userservice.api;


import io.getarrays.userservice.domain.User;
import io.getarrays.userservice.service.auth.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class AuthenticationController {

    private final AuthService authService;

    @RequestMapping("/login")
    public Map<String, String> userLogin(@RequestBody User user) throws Exception {
        return authService.userLogin(user.getUsername(),user.getPassword());
    }
}
