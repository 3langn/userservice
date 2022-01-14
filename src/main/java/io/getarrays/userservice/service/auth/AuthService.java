package io.getarrays.userservice.service.auth;

import java.util.Map;

public interface AuthService {
    Map<String, String> userLogin(String username, String password) throws Exception;
}
