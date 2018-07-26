package org.shersfy.zuul.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.Banner.Mode;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.ComponentScan;

@EnableZuulProxy // EnableZuulServer增强 eureka、ribbon
//@EnableZuulServer
//@EnableDiscoveryClient
@ComponentScan("org.shersfy.zuul")
@SpringBootApplication
public class ZuulApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(ZuulApplication.class);
		app.setBannerMode(Mode.OFF);
		app.run(args);
	}

}
