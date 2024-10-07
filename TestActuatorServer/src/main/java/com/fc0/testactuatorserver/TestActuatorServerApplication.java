package com.fc0.testactuatorserver;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableAdminServer
@SpringBootApplication
public class TestActuatorServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestActuatorServerApplication.class, args);
    }

}
