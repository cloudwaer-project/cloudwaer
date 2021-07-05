package com.cloudwaer.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @ClassName OAuth2FeignClient
 * @Description TODO
 * @Author jiushiboy
 * @Date 2021/7/5 15:48
 * @Version 1.0
 **/
@FeignClient(name = "authorization-server",url = "localhost:9999")
public interface OAuth2FeignClient {
    @PostMapping("/oauth/token")
    ResponseEntity<JwtToken> getToken(
            @RequestParam("grant_type") String grantType,
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("login_type") String loginType,
            // 该属性根据Basic Auth生成,第三方客户端加密而成
            @RequestHeader("Authorization") String basicToken);
}
