package bg.fon.huntingassociation.controller.request;

public class JWTAuthLoginRequest {

    String username;
    String password;

    public JWTAuthLoginRequest(String usernameOrEmail, String password) {
        this.username = usernameOrEmail;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
