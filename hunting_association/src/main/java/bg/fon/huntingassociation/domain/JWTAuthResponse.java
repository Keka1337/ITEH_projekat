package bg.fon.huntingassociation.domain;

public class JWTAuthResponse {

    String accessToken;

    public JWTAuthResponse() {
    }

    public JWTAuthResponse(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
