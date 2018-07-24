package org.shersfy.register.boot;

import org.springframework.boot.Banner.Mode;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class RegistrationCenter {
    
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(RegistrationCenter.class);
        app.setBannerMode(Mode.OFF);
        app.run(args);
    }

}
