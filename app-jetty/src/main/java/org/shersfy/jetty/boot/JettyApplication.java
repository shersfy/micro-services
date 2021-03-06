package org.shersfy.jetty.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.Banner.Mode;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@EnableDiscoveryClient
@ComponentScan("org.shersfy.jetty")
@SpringBootApplication
public class JettyApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(JettyApplication.class);
		app.setBannerMode(Mode.OFF);
		app.run(args);
	}

}
