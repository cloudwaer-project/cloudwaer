package com.cloudwaer.common.jiushiboy.config.swagger;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @ClassName SwaggerProperties
 * @Description TODO
 * @Author jiushiboy
 * @Date 2021/6/11 15:48
 * @Version 1.0
 **/
@Data
@ConfigurationProperties(prefix = "swagger2")
public class SwaggerProperties {
    /**
     * 包扫描
     */
    private String basePackage;

    /**
     * 联系人名称
     */
    private String name;

    /**
     * 作者主页
     */
    private String url;

    /**
     * 邮箱
     */
    private String email;

    /**
     * api标题
     */
    private String title;

    /**
     * api描述
     */
    private String description;

    /**
     * api版本号
     */
    private String version;

    /**
     * api服务团队
     */
    private String termsOfServiceUrl;
}
