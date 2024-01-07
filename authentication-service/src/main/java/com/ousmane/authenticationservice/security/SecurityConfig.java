package com.ousmane.authenticationservice.security;

import com.ousmane.authenticationservice.exceptions.JwtAccessDeniedHandler;
import com.ousmane.authenticationservice.exceptions.JwtAuthenticationEntryPoint;
import com.ousmane.authenticationservice.jwt.config.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtFilter;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final AuthenticationProvider authenticationProvider;

    private static final String[] URLS = {
            "/v1/auth/**", "/actuator/**", "/v2/api-docs",
            "/v3/api-docs", "/v3/api-docs/**", "/swagger-resources",
            "/swagger-resources/**", "/configuration/ui",
            "/configuration/security", "/swagger-ui/**",
            "/webjars/**", "/swagger-ui.html"};

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers(URLS).permitAll();
                    auth.requestMatchers("/api/secured/endpoint/admin").hasAuthority("ADMIN");
                    auth.requestMatchers("/api/secured/endpoint/user").hasAuthority("USER");
                    auth.requestMatchers("/api/secured/endpoint/user_admin").hasAnyAuthority("USER", "ADMIN");
                    auth.requestMatchers("/v1/app/demo-controller").hasAnyAuthority("USER", "ADMIN");
                    auth.requestMatchers("/v1/app/students").hasRole("ADMIN");
                    auth.anyRequest().authenticated();
                })
                .exceptionHandling(ex -> {
                    ex.accessDeniedHandler(jwtAccessDeniedHandler);
                    ex.authenticationEntryPoint(jwtAuthenticationEntryPoint);
                })
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
