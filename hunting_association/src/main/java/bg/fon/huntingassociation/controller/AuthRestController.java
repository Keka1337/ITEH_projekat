package bg.fon.huntingassociation.controller;

import bg.fon.huntingassociation.domain.JWTAuthResponse;
import bg.fon.huntingassociation.domain.dtos.LoginDto;
import bg.fon.huntingassociation.service.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthRestController {


    private final AuthService authService;
    private final Logger LOG = LoggerFactory.getLogger(AuthRestController.class.getName());

    public AuthRestController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<JWTAuthResponse> authenticate(@RequestBody LoginDto loginDto){
        LOG.info("Sending authentication request: username:" + loginDto.getUsername() + ", password: " + loginDto.getPassword());
        String token = authService.login(loginDto);
        LOG.info("Generated token:" + token);

        JWTAuthResponse jwtAuthResponse = new JWTAuthResponse();
        jwtAuthResponse.setAccessToken(token);

        return ResponseEntity.ok(jwtAuthResponse);
    }
}
