package org.shersfy.tcc.boot;

import org.springframework.boot.Banner.Mode;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TccBoot {
    
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(TccBoot.class);
        app.setBannerMode(Mode.OFF);
        app.run(args);
    }

}
