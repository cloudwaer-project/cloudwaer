package com.cloudwaer;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @ClassName CategoryApplicationRun
 * @Description TODO
 * @Author jiushiboy
 * @Date 2021/6/22 9:06
 * @Version 1.0
 **/
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan(value = {"com.com.cloudwaer.mapper"})
public class CategoryApplicationRun {
    public static void main(String[] args) {
        SpringApplication.run(CategoryApplicationRun.class, args);
    }
}
