package bg.fon.huntingassociation.service;

import bg.fon.huntingassociation.domain.User;
import bg.fon.huntingassociation.exception.AuthenticationException;
import bg.fon.huntingassociation.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User login(String username, String password) throws AuthenticationException {
        User user = userRepository.finbByUsernameAndPassword(username,password);
        if(user != null)
            return user;
        else
            throw new AuthenticationException("Authentication failed! Invalid username or password.");
    }
}
