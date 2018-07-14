package org.shersfy.sidecar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.Banner.Mode;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.sidecar.EnableSidecar;

@SpringBootApplication
@EnableSidecar
@EnableEurekaClient
public class SidecarProxyServer {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(SidecarProxyServer.class);
		app.setBannerMode(Mode.OFF);
		app.run(args);
	}

}
