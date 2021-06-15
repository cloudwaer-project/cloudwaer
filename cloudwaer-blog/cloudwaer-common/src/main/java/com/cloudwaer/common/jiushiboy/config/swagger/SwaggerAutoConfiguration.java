package com.cloudwaer.common.jiushiboy.config.swagger;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.List;

/**
 * @ClassName SwaggerAutoConfiguration
 * @Description TODO
 * @Author jiushiboy
 * @Date 2021/6/11 15:35
 * @Version 1.0
 **/
@Configuration
@EnableSwagger2
@EnableConfigurationProperties(com.cloudwaer.common.jiushiboy.config.swagger.SwaggerProperties.class)
public class SwaggerAutoConfiguration {

    private SwaggerProperties swaggerProperties;

    public SwaggerAutoConfiguration(SwaggerProperties swaggerProperties) {
        this.swaggerProperties = swaggerProperties;
    }

    @Bean
    public Docket docket() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage(swaggerProperties.getBasePackage()))
                .paths(PathSelectors.any())
                .build();
        // 安全配置
        docket.securitySchemes(securitySchemes()) //安全规则
                .securityContexts(securityContexts()); //安全配置上下文
        return docket;
    }

    private ApiInfo apiInfo() {
        ApiInfo info = new ApiInfoBuilder().contact(
                new Contact(swaggerProperties.getName(), swaggerProperties.getUrl(), swaggerProperties.getUrl())
        ).title(swaggerProperties.getTitle())
                .description(swaggerProperties.getDescription())
                .version(swaggerProperties.getVersion())
                .termsOfServiceUrl(swaggerProperties.getTermsOfServiceUrl())
                .build();
        return info;
    }

    /**
     * 安全的规则
     *
     * @return
     */
    private List<? extends SecurityScheme> securitySchemes() {
        return Arrays.asList(new ApiKey("Authorization", "Authorization", "Authorization"));
    }

    /**
     * 安全的上下文
     *
     * @return
     */
    private List<SecurityContext> securityContexts() {
        return Arrays.asList(new SecurityContext(
                Arrays.asList(new SecurityReference("Authorization", new AuthorizationScope[]{new AuthorizationScope("global", "accessResource")})),
                PathSelectors.any()
        ));
    }


}
