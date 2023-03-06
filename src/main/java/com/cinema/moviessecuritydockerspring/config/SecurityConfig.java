package com.cinema.moviessecuritydockerspring.config;

import com.cinema.moviessecuritydockerspring.security.CustomUserDetailsService;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Resource
    private CustomUserDetailsService customUserDetailsService;

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .authenticationProvider(daoAuthenticationProvider())
                .build();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(customUserDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeHttpRequests((authorize) ->
                {
                    try {
                        authorize.requestMatchers("/register").permitAll()
                                .requestMatchers("/login").permitAll()
                                .requestMatchers(HttpMethod.GET, "/movie").permitAll()
                                .requestMatchers(HttpMethod.GET, "/movie/name").permitAll()
                                .requestMatchers(HttpMethod.POST, "/movie/new").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.POST, "/details/new").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.PUT, "/movie").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.PUT, "/details").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/movie/name").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/details/name").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.GET, "/category").permitAll()
                                .requestMatchers(HttpMethod.POST, "/category/new").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/category/name").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/category/id").hasRole("ADMIN")
                                .requestMatchers("/user/**").hasRole("ADMIN")
                                .requestMatchers("/role/**").hasRole("ADMIN")
                                .anyRequest()
                                .authenticated()
                                .and()
                                .httpBasic();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
        return http.build();
    }
}
