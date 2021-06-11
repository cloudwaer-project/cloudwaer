package com.cloudwaer.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

/**
 * @ClassName GatewayUrlConfig  配置放行的访问路径
 * @Description TODO
 * @Author jiushiboy
 * @Date 2021/6/11 13:46
 * @Version 1.0
 **/
@Configuration
@ConfigurationProperties(prefix = "norequire")
public class GatewayUrlConfig {
    private Set<String> urls;

    public Set<String> getUrls() {
        return urls;
    }

    public void setUrls(Set<String> urls) {
        this.urls = urls;
    }
}
