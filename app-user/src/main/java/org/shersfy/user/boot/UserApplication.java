package org.shersfy.user.boot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.Banner.Mode;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("org.shersfy.user")
@MapperScan("org.shersfy.user.mapper")
@SpringBootApplication
public class UserApplication {

	public static void main(String[] args) {
	    SpringApplication app = new SpringApplication(UserApplication.class);
	    app.setBannerMode(Mode.OFF);
        app.run(args);
	}
}
