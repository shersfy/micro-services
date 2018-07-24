package org.shersfy.config.boot;

import org.springframework.boot.Banner.Mode;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@EnableDiscoveryClient
@SpringBootApplication
public class ConfigurationCenter {
    
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(ConfigurationCenter.class);
        app.setBannerMode(Mode.OFF);
        app.run(args);
    }

}
