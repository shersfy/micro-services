package org.shersfy.eureka.boot;

import org.springframework.boot.Banner.Mode;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaServer {
    
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(EurekaServer.class);
        app.setBannerMode(Mode.OFF);
        app.run(args);
    }

}
