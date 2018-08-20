package riverway.dto;

import java.util.Objects;

public class KakaoDto {

    private String access_token;

    private String token_type;

    private String refresh_token;

    private String expires_in;

    private String scope;

    private String refresh_token_expires_in;

    public KakaoDto() {
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getToken_type() {
        return token_type;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public String getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(String expires_in) {
        this.expires_in = expires_in;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getRefresh_token_expires_in() {
        return refresh_token_expires_in;
    }

    public void setRefresh_token_expires_in(String refresh_token_expires_in) {
        this.refresh_token_expires_in = refresh_token_expires_in;
    }

    public String makeAuthorization() {
        return token_type + " " + access_token;
    }

    @Override
    public String toString() {
        return "KakaoDto{" +
                "access_token='" + access_token + '\'' +
                ", token_type='" + token_type + '\'' +
                ", refresh_token='" + refresh_token + '\'' +
                ", expires_in='" + expires_in + '\'' +
                ", scope='" + scope + '\'' +
                ", refresh_token_expires_in='" + refresh_token_expires_in + '\'' +
                '}';
    }
}
