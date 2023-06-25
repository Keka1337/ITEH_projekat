package bg.fon.huntingassociation.controller;

import bg.fon.huntingassociation.exception.AuthenticationException;
import bg.fon.huntingassociation.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login/{username}/{password}")
    public ResponseEntity<?> login(@PathVariable("username") String username,
                                   @PathVariable("password") String password,
                                   @RequestParam(defaultValue = "id") String sortBy,
                                   @RequestParam(required = false) String filter) {
        try {
            return new ResponseEntity<>(userService.login(username,password), HttpStatus.OK);
        } catch (AuthenticationException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
