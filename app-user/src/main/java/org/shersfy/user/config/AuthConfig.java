package org.shersfy.user.config;

import javax.annotation.Resource;

import org.shersfy.user.service.UserService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

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
     .formLogin()
        .loginPage("/login")
        .defaultSuccessUrl("/index.html")
        .failureUrl("/loginError")
		.usernameParameter("txtUserCd")
		.passwordParameter("txtUserPwd")
        .permitAll()
        .and()
        .logout()
        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
        .logoutSuccessUrl("/index.html")
		.deleteCookies("JSESSIONID")
		.invalidateHttpSession(true)
		.permitAll()
		.and()
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
				return rawPassword.equals(encodedPassword);
			}
			
			@Override
			public String encode(CharSequence rawPassword) {
				return rawPassword.toString();
			}
		});
	}
	 
	public void globalAuthConfig(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(new PasswordEncoder() {
			
			@Override
			public boolean matches(CharSequence rawPassword, String encodedPassword) {
				return false;
			}
			
			@Override
			public String encode(CharSequence rawPassword) {
				return rawPassword.toString();
			}
		});
	}


}
