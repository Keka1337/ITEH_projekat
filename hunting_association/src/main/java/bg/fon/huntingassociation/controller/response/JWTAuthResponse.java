package bg.fon.huntingassociation.controller.response;

public class JWTAuthResponse {

    String accessToken;
    String role;


    public JWTAuthResponse() {
    }

    public JWTAuthResponse(String accessToken) {
        this.accessToken = accessToken;
    }

    public JWTAuthResponse(String accessToken, String role) {
        this.accessToken = accessToken;
        this.role = role;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
