package org.shersfy.user.config;

import javax.annotation.Resource;

import org.apache.tomcat.util.security.MD5Encoder;
import org.shersfy.user.service.UserService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.DigestUtils;

@EnableWebSecurity
@Configuration
public class AuthConfig extends WebSecurityConfigurerAdapter {
	
	@Resource
	private UserService userService;
	
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.authorizeRequests()
		.antMatchers("/css/**","/staic/**", "/js/**","/images/**").permitAll()
		.antMatchers("/index.html", "/login").permitAll()
		.and()
		// login
		.formLogin()
		.loginPage("/login")
		.defaultSuccessUrl("/index.html")
		.failureUrl("/loginError")
		.usernameParameter("txtUserCd")
		.passwordParameter("txtUserPwd")
		.permitAll()
		.and()
		// logout
		.logout()
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		.logoutSuccessUrl("/index.html")
		.deleteCookies("JSESSIONID")
		.invalidateHttpSession(true)
		.permitAll()
		.and()
		// session invalid
		.sessionManagement()
		.invalidSessionUrl("/expired")
		.maximumSessions(1)
		.maxSessionsPreventsLogin(true)
		.expiredUrl("/expired");

		httpSecurity.logout().permitAll();

	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService).passwordEncoder(new PasswordEncoder() {
			
			@Override
			public boolean matches(CharSequence rawPassword, String encodedPassword) {
				String md5 = DigestUtils.md5DigestAsHex(rawPassword.toString().getBytes());
				return encodedPassword.equals(md5);
			}
			
			@Override
			public String encode(CharSequence rawPassword) {
				String encoded = MD5Encoder.encode(rawPassword.toString().getBytes());
				return encoded;
			}
		});
	}

}
