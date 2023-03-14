package com.cuidl.pip.core;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author cuidl
 */
@SpringBootApplication
@ComponentScan("com.cuidl.pip")
public class ServerCoreApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServerCoreApplication.class, args);
    }
}
