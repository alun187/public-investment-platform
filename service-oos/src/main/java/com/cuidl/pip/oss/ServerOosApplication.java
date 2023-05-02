package com.cuidl.pip.oss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author cuidl
 */
@SpringBootApplication
@ComponentScan("com.cuidl.pip")
public class ServerOosApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServerOosApplication.class, args);
    }
}
