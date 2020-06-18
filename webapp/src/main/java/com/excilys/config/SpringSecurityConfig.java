package com.excilys.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
		auth.inMemoryAuthentication().withUser("DBenamara").password(passwordEncoder().encode("azerty")).roles("USER");
		auth.inMemoryAuthentication().withUser("CClain").password(passwordEncoder().encode("azerty")).roles("ADMIN");
	}
	
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {

		httpSecurity
		.csrf()
		.disable();

		httpSecurity
		.authorizeRequests()
		.antMatchers("/", "/login", "/welcome","/addUser")
		.permitAll();

		httpSecurity
		.authorizeRequests()
		.antMatchers("/dashboard")
		.access("hasAnyRole('USER', 'ADMIN')");

		httpSecurity
		.authorizeRequests()
		.antMatchers("/editComputer", "/addComputer")
		.access("hasRole('ADMIN')");

		httpSecurity.authorizeRequests().and().formLogin()
				.loginProcessingUrl("/j_spring_security_check")
				.loginPage("/login")
				.defaultSuccessUrl("/dashboard")
				.failureUrl("/login?error=true")
				.usernameParameter("username")
				.passwordParameter("password")
				.and().logout()
				.logoutUrl("/logout")
				.logoutSuccessUrl("/home");
	}
}
