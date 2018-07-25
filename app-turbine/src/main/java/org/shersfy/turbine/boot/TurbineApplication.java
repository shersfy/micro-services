package org.shersfy.turbine.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.Banner.Mode;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.turbine.EnableTurbine;
import org.springframework.context.annotation.ComponentScan;

@EnableDiscoveryClient
@EnableTurbine
@ComponentScan("org.shersfy.turbine")
@SpringBootApplication
public class TurbineApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(TurbineApplication.class);
		app.setBannerMode(Mode.OFF);
		app.run(args);
	}

}
