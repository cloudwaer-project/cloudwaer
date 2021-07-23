package com.cloudwaer;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.util.List;

/**
 * @ClassName AdminApplicationRun
 * @Description TODO
 * @Author jiushiboy
 * @Date 2021/6/18 15:01
 * @Version 1.0
 **/
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.cloudwaer.mapper")
@EnableFeignClients
public class AdminApplicationRun {
    public static void main(String[] args) {
        SpringApplication.run(AdminApplicationRun.class, args);
    }

}

