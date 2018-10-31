package com.xdq.blog.manager;

import com.xdq.blog.common.annotation.EnableDao;
import com.xdq.blog.manager.properties.BlogProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableDao
@EnableConfigurationProperties({BlogProperties.class})
public class ManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManagerApplication.class, args);
    }
}
