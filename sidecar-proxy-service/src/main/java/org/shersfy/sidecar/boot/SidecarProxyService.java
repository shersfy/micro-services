package org.shersfy.sidecar.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.Banner.Mode;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.sidecar.EnableSidecar;

@EnableSidecar
@EnableEurekaClient
@SpringBootApplication
public class SidecarProxyService {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(SidecarProxyService.class);
		app.setBannerMode(Mode.OFF);
		app.run(args);
	}

}
