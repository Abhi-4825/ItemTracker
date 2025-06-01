package com.project.ItemTracker.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.project.ItemTracker.Service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class securityConfig {
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
     @Autowired
	 private JwtAuthFilter jwtAuthFilter;
	
	@Bean
	public SecurityFilterChain filterchain(HttpSecurity http) throws Exception{
		http.authenticationProvider(authenticationProvider());
		http.csrf(csrf->csrf.disable());
		http.authorizeHttpRequests(auth->auth.requestMatchers(HttpMethod.POST,"/register","/generateToken").permitAll().anyRequest().authenticated())
		.sessionManagement(sess->sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		.addFilterBefore(jwtAuthFilter,UsernamePasswordAuthenticationFilter.class);
		
		return http.build();
		
	}
	@Bean
	public DaoAuthenticationProvider authenticationProvider(){
		DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
		provider.setUserDetailsService(customUserDetailsService);
		provider.setPasswordEncoder(passwordEncoder());
		return provider;
		
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
		
	}

}
