package bg.fon.huntingassociation.service;

import bg.fon.huntingassociation.security.JwtTokenProvider;
import bg.fon.huntingassociation.controller.response.JWTAuthResponse;
import bg.fon.huntingassociation.controller.request.JWTAuthLoginRequest;
import bg.fon.huntingassociation.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthService(AuthenticationManager authenticationManager, UserRepository userRepository, PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public JWTAuthResponse login(JWTAuthLoginRequest JWTAuthRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                JWTAuthRequest.getUsername(), JWTAuthRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenProvider.generateToken(authentication);
        String role = userRepository.findRoleByUsername(JWTAuthRequest.getUsername());

        JWTAuthResponse response = new JWTAuthResponse();
        response.setAccessToken(token);
        response.setRole(role);
        return response;
    }
}
