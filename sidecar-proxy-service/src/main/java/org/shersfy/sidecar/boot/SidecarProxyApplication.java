package org.shersfy.sidecar.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.Banner.Mode;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.sidecar.EnableSidecar;

@EnableSidecar
@EnableDiscoveryClient
@SpringBootApplication
public class SidecarProxyApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(SidecarProxyApplication.class);
		app.setBannerMode(Mode.OFF);
		app.run(args);
	}

}
