package riverway.dto;

public class KakaoDto {

    String access_token;

    String token_type;

    String refresh_token;

    String expires_in;

    String scope;

    String refresh_token_expires_in;

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
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((access_token == null) ? 0 : access_token.hashCode());
        result = prime * result + ((expires_in == null) ? 0 : expires_in.hashCode());
        result = prime * result + ((refresh_token == null) ? 0 : refresh_token.hashCode());
        result = prime * result + ((refresh_token_expires_in == null) ? 0 : refresh_token_expires_in.hashCode());
        result = prime * result + ((scope == null) ? 0 : scope.hashCode());
        result = prime * result + ((token_type == null) ? 0 : token_type.hashCode());
        return result;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        KakaoDto other = (KakaoDto) obj;
        if (access_token == null) {
            if (other.access_token != null)
                return false;
        } else if (!access_token.equals(other.access_token))
            return false;
        if (expires_in == null) {
            if (other.expires_in != null)
                return false;
        } else if (!expires_in.equals(other.expires_in))
            return false;
        if (refresh_token == null) {
            if (other.refresh_token != null)
                return false;
        } else if (!refresh_token.equals(other.refresh_token))
            return false;
        if (refresh_token_expires_in == null) {
            if (other.refresh_token_expires_in != null)
                return false;
        } else if (!refresh_token_expires_in.equals(other.refresh_token_expires_in))
            return false;
        if (scope == null) {
            if (other.scope != null)
                return false;
        } else if (!scope.equals(other.scope))
            return false;
        if (token_type == null) {
            if (other.token_type != null)
                return false;
        } else if (!token_type.equals(other.token_type))
            return false;
        return true;
    }


    @Override
    public String toString() {
        return "KakaoOauthDto [access_token=" + access_token + ", token_type=" + token_type + ", refresh_token="
                + refresh_token + ", expires_in=" + expires_in + ", scope=" + scope + ", refresh_token_expires_in="
                + refresh_token_expires_in + "]";
    }
}
