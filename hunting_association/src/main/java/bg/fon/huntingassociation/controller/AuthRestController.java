package bg.fon.huntingassociation.controller;

import bg.fon.huntingassociation.controller.response.JWTAuthResponse;
import bg.fon.huntingassociation.controller.request.JWTAuthLoginRequest;
import bg.fon.huntingassociation.service.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
public class AuthRestController {


    private final AuthService authService;
    private final Logger LOG = LoggerFactory.getLogger(AuthRestController.class.getName());

    public AuthRestController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<JWTAuthResponse> authenticate(@RequestBody JWTAuthLoginRequest JWTAuthRequest){
        LOG.info("Sending authentication request: username:" + JWTAuthRequest.getUsername() +
                ", password: " + JWTAuthRequest.getPassword());

        return ResponseEntity.ok(authService.login(JWTAuthRequest));
    }
}
