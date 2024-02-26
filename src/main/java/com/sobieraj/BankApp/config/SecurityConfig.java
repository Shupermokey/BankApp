package com.sobieraj.BankApp.config;

import java.util.Collections;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import jakarta.servlet.http.HttpServletRequest;

@Configuration
public class SecurityConfig {

	@Bean
	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
		
		http.cors().configurationSource(new CorsConfigurationSource() {
			
			@Override
			public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
				CorsConfiguration config = new CorsConfiguration();
				config.setAllowedOrigins(Collections.singletonList("http://localhost:9090"));
				config.setAllowedMethods(Collections.singletonList("*"));
				config.setAllowCredentials(true);
				config.setAllowedHeaders(Collections.singletonList("*"));
				config.setMaxAge(3600L);
				return config;
			}
		})
		.and()
		.csrf().disable()
		.authorizeHttpRequests()
		.requestMatchers(HttpMethod.GET, "/", "/createAccount").permitAll()
		.requestMatchers(HttpMethod.POST, "/createAccount").authenticated()
		.requestMatchers("/css/**","/js/**","/contact" ).permitAll()
		.and().formLogin()
		.and().httpBasic();
		
		return http.build();
		
	}
	
	
//	@Bean
//	public InMemoryUserDetailsManager  userDetailsService() {
//		UserDetails admin = User
//				.withUsername("admin")
//				.password("test")
//				.authorities("admin")
//				.build();
//		
//		UserDetails user = User
//				.withUsername("user")
//				.password("test")
//				.authorities("read")
//				.build();
//		
//		return new InMemoryUserDetailsManager(admin,user);
//	}
	
//	@Bean
//	public UserDetailsService userDetailsService(DataSource datasource) {
//		return new JdbcUserDetailsManager(datasource);
//	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
