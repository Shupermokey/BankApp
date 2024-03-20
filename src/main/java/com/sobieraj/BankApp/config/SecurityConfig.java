package com.sobieraj.BankApp.config;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;



@Configuration
public class SecurityConfig {

	@Bean
	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {

		http
		.csrf(csrf -> csrf.disable())
		.authorizeHttpRequests(auth -> auth
				.requestMatchers("/templates/**" , "/css/**","/js/**", "/images/**" ).permitAll()
				.requestMatchers(HttpMethod.GET, "/",
						"/createAccount",
						"/forgotPassword",
						"/createBankAccount",
						"/deposit"
						,"/deleteAccount",
						"/deleteAccount/**",
						"/openCreditCard",
						"/manageCreditCards",
						"/manageTransaction",
						"/homePage").permitAll()
				.requestMatchers(HttpMethod.POST, "/logins").permitAll()
				.requestMatchers(HttpMethod.POST,"/createAccount",
						"/openCreditCard",
						"/createBankAccount",
						"/manageAccount", 
						"/manageTransaction",
						"/depositAmount").permitAll()
				)
		.formLogin(form -> form.loginPage("/").permitAll())
		.logout(logout -> logout.permitAll())
		.exceptionHandling(configurer -> 
				configurer.accessDeniedPage("/access-denied"))
		.httpBasic();
		
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
	
	@Bean
	public UserDetailsService userDetailsService(DataSource datasource) {
		return new JdbcUserDetailsManager(datasource);
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
