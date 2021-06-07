package com.cloudwaer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class GatewayApplicationRun {
    public static void main(String[] args) {
        SpringApplication.run(GatewayApplicationRun.class, args);
    }
}
