package com.simplelibrary.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
	WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedMethods("*").allowedOrigins("*");
			}
		};
	}
	
	@Bean
	SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception{
        http.csrf((csrf) -> csrf.disable())
        	.authorizeHttpRequests((authorizeHttpRequests) ->
 				authorizeHttpRequests
 					.requestMatchers(HttpMethod.GET,"api/livros","api/livros/**","api/autores","api/autores/**","api/categoria","api/categoria/**","api/avaliacao/**").permitAll()
 					.requestMatchers(HttpMethod.POST,"api/cadastro", "api/login").permitAll()
 					.requestMatchers("api/adm","api/adm/**").hasAuthority("ADMIN")
 					.requestMatchers("api/user","api/user/**").hasAnyAuthority("ADMIN","USER")
 					.anyRequest().authenticated());
        http.addFilterBefore(new SecurityFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
	}

	@Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

	


}
