package com.matt1479.securingwebitems;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
// import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration 
@EnableWebSecurity 
@EnableMethodSecurity 
public class WebSecurityConfig {

    @Bean 
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) {
        httpSecurity
            .authorizeHttpRequests((requests) -> requests
                // "/" and "/home" do not require authentication
                .requestMatchers("/", "/home").permitAll()

                // Require role of ADMIN to perform DELETE on /items/*
                // .requestMatchers(HttpMethod.DELETE, "/items/*").hasRole("ADMIN")

                // "/items/**" paths must be authenticated
                .requestMatchers("/items/**").authenticated()

                // All other paths must be authenticated
                .anyRequest().authenticated()
            )
            .formLogin((form) -> form
                // "/login" does not require authentication
                .loginPage("/login").permitAll()
            )
            .logout((logoutConfigurer) -> logoutConfigurer.permitAll());
            // .logout(LogoutConfigurer::permitAll);
        
        return httpSecurity.build();
    }

    @Bean 
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean 
    UserDetailsService userDetailsService(UserService userService, PasswordEncoder encoder) {
        // UserDetailsService sets up an in-memory user store with two users:
        // sampleuser, sampleadmin
        return new InMemoryUserDetailsManager(
            userService.getUsers(encoder).values()
        );
    }

}
