package com.cuidl.pip.sms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author cuidl
 */
@SpringBootApplication
@ComponentScan("com.cuidl.pip")
public class ServerSmsApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServerSmsApplication.class, args);
    }
}
