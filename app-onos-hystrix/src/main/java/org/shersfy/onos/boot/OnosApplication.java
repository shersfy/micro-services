package org.shersfy.onos.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.Banner.Mode;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@EnableDiscoveryClient
@EnableFeignClients
@EnableCircuitBreaker
@ComponentScan("org.shersfy.onos")
@SpringBootApplication
public class OnosApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(OnosApplication.class);
		app.setBannerMode(Mode.OFF);
		app.run(args);
	}

}
