package org.shersfy.accel.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.Banner.Mode;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@EnableDiscoveryClient
@ComponentScan("org.shersfy.accel")
@SpringBootApplication
public class AccelApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(AccelApplication.class);
		app.setBannerMode(Mode.OFF);
		app.run(args);
	}

}
