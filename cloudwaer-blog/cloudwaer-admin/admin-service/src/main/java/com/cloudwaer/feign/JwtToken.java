package com.cloudwaer.feign;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @ClassName JwtToken
 * @Description TODO
 * @Author jiushiboy
 * @Date 2021/7/5 15:50
 * @Version 1.0
 **/
@Data
public class JwtToken {

    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("token_type")
    private String tokenType;

    @JsonProperty("refresh_token")
    private String refreshToken;

    @JsonProperty("expires_in")
    private Long expiresIn;

    private String scope;

    private String jti;

}
