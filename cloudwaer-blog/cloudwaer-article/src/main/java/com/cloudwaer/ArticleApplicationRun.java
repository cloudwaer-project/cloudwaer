package com.cloudwaer;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @ClassName ArticleApplicationRun
 * @Description TODO
 * @Author jiushiboy
 * @Date 2021/6/7 10:37
 * @Version 1.0
 **/
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan(value = {"com.cloudwaer.mapper"})
public class ArticleApplicationRun {
    public static void main(String[] args) {
        SpringApplication.run(ArticleApplicationRun.class, args);
    }
}
