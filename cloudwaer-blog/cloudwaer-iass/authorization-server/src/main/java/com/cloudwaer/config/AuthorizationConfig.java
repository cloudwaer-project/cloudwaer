package com.cloudwaer.config;

import com.cloudwaer.service.impl.UserServiceDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;
import org.springframework.stereotype.Component;

/**
 * @ClassName AuthorizationConfig
 * @Description TODO
 * @Author jiushiboy
 * @Date 2021/6/10 11:15
 * @Version 1.0
 **/
@EnableAuthorizationServer
@Configuration
public class AuthorizationConfig extends AuthorizationServerConfigurerAdapter {


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Qualifier(value = "userServiceDetailsServiceImpl")
    @Autowired
    private UserServiceDetailsServiceImpl userServiceDetailsService;


    /**
     * 添加第三方客户端
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory().withClient("cloudwaer-api") //第三方客户端
                .secret(passwordEncoder.encode("cloudwaer-secret")) //第三方客户端秘钥
                .scopes("all") //第三方授权范围
                .authorizedGrantTypes("password", "refresh_token") //授权模式
                .accessTokenValiditySeconds(7 * 24 * 3600)  // token一个星期过期
                .refreshTokenValiditySeconds(30 * 24 * 3600) //refresh token 一个月过期
                .and()
                .withClient("inside-app")
                .secret(passwordEncoder.encode("inside-secret"))
                .scopes("all")
                .accessTokenValiditySeconds(7 * 24 * 3600);
        super.configure(clients);
    }

    /**
     * 配置验证管理器  ---> UserdetailsService
     * authenticationManager 认证管理 , 配置在WebSecurityConfig 配置类中
     * userDetailsService 校验用户是否正确以及获取用户所有权限
     * tokenStore 存储token的方式
     * tokenEnhancer 产生token
     *
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager) //认证管理
                .userDetailsService(userServiceDetailsService) // 获取用户权限等
                .tokenStore(jwtTokenStore()) // token存储方式
                .tokenEnhancer(jwtAccessTokenConverter());// 产生token
        super.configure(endpoints);
    }

    /**
     * jwt  token 存储方式
     * @return
     */
    private TokenStore jwtTokenStore() {
        JwtTokenStore jwtTokenStore=new JwtTokenStore(jwtAccessTokenConverter());
        return jwtTokenStore;
    }


    /**
     * 读取jwt秘钥
     *
     * @return
     */
    private JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        ClassPathResource classPathResource = new ClassPathResource("cloudwaer.jks");
        // 提供私钥库的文件路径,提供秘钥库访问密码
        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(classPathResource, "cloudwaer".toCharArray());
        // 获取别名为cloudwaer的秘钥,并且提供秘钥密码
        jwtAccessTokenConverter.setKeyPair(keyStoreKeyFactory.getKeyPair("cloudwaer", "cloudwaer".toCharArray()));
        return jwtAccessTokenConverter;
    }
}
