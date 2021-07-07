package com.cloudwaer;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @ClassName com.cloudwaer.TagsApplicationRun
 * @Description TODO
 * @Author jiushiboy
 * @Date 2021/6/22 18:00
 * @Version 1.0
 **/
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan(value = {"com.cloudwaer.mapper"})
public class TagsApplicationRun {
    public static void main(String[] args) {
        SpringApplication.run(TagsApplicationRun.class, args);
    }
}
