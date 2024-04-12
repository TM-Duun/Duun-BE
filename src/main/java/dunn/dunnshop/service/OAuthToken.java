package dunn.dunnshop.service;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
public class OAuthToken {
    private String access_token;
    private String token_type;
    private String refresh_token;
    private int expires_in;
    private String scope;
    private int refresh_token_expries_in;
}