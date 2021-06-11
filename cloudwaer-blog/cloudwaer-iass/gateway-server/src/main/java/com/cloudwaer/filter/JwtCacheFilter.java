package com.cloudwaer.filter;

import com.alibaba.fastjson.JSONObject;
import com.cloudwaer.config.GatewayUrlConfig;
import com.google.common.net.HttpHeaders;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Set;

/**
 * @ClassName JwtCacheFilter   实现了 全局过滤,并且定义了拦截的顺序
 * @Description TODO
 * @Author jiushiboy
 * @Date 2021/6/11 11:09
 * @Version 1.0
 **/

@Component
public class JwtCacheFilter implements GlobalFilter, Ordered {

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 放行的接口
     */
    @Autowired
    private GatewayUrlConfig gatewayUrlConfig;


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 1.接口是否需要token才能访问
        if (!isRequiredToken(exchange)) {
            return chain.filter(exchange); //不需要token直接放行
        }
        // 2.取出用户token
        String token = getUserToken(exchange);
        // 3.判断用户token 是否有效
        if (StringUtils.isEmpty(token)) {
            return buildNoAuthorizationResult(exchange);
        }
        Boolean hashKey = redisTemplate.hasKey(token);
        if (null != hashKey && hashKey) {
            return chain.filter(exchange); //token 有效直接放行
        }
        return buildNoAuthorizationResult(exchange);
    }

    /**
     * 响应给用户一个没有token不存在的错误信息
     * @param exchange
     * @return
     */
    private Mono<Void> buildNoAuthorizationResult(ServerWebExchange exchange) {
        ServerHttpResponse response = exchange.getResponse();
        response.getHeaders().set("Content-type","application/json");
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("error","NoAuthorization");
        jsonObject.put("errorMsg","Token is Null or Error");
        DataBuffer wrap = response.bufferFactory().wrap(jsonObject.toJSONString().getBytes());
        return response.writeWith(Flux.just(wrap));
    }

    /**
     * 从请求头获取用户的token
     * @param exchange
     * @return
     */
    private String getUserToken(ServerWebExchange exchange) {
        String token = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        return token == null ? null : token.replace("bearer", "");
    }

    /**
     * 判断接口是否需要token才能访问
     *
     * @param exchange
     * @return
     */
    private boolean isRequiredToken(ServerWebExchange exchange) {
        String path = exchange.getRequest().getURI().getPath();
        Set<String> noRequiredTokenUris = gatewayUrlConfig.getUrls();
        if (noRequiredTokenUris.contains(path)) {
            return false; //不需要token返回false
        }
        return Boolean.TRUE;
    }

    /**
     * 拦截顺序 0 代表最前
     * @return
     */
    @Override
    public int getOrder() {
        return 0;
    }
}
