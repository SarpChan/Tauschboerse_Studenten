package de.hsrm.mi.swtpro.backend.controller.login;

/**
 * contains the current token for responses
 */
public class JWTTokenResponse {
    private String token;

    public JWTTokenResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
