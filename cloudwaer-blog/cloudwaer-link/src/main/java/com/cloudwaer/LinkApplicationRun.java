package com.cloudwaer;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author wenchang
 * @Date 2021/7/13 9:22
 * @Version 1.0
 */
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan(basePackages = {"com.cloudwaer.mapper"})
public class LinkApplicationRun {
    public static void main(String[] args) {
        SpringApplication.run(LinkApplicationRun.class, args);
    }
}
