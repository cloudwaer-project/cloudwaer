package com.cloudwaer.common.jiushiboy.config.jetcache;

import com.alicp.jetcache.anno.config.EnableCreateCacheAnnotation;
import com.alicp.jetcache.anno.config.EnableMethodCache;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName JetcacheConfig
 * @Description TODO
 * @Author jiushiboy
 * @Date 2021/6/11 15:11
 * @Version 1.0
 **/
@Configuration
@EnableCreateCacheAnnotation
@EnableMethodCache(basePackages = "com.cloudwaer.service.impl")
public class JetcacheConfig {

}
