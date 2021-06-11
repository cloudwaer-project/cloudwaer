package com.cloudwaer.comment;

import com.cloudwaer.comment.quantity.CommentQT;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

/**
 * @Author A_Nan
 * @Date 21/06/10 下午 3:37
 * @ClassName 启动类
 */

@SpringBootApplication
@EnableDiscoveryClient
public class CommentRun {
    public static void main(String[] args) {
        SpringApplication.run(CommentRun.class, args);
    }

//    @Bean
//    public CommentQT getCommentQT() {
//        return new CommentQT();
//    }
}
