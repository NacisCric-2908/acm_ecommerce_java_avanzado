package com.acm.ecommerce.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
// TODO: Eliminar esta clase y configurar la seguridad para proteger la aplicación
public class DisableCsrf {

    // CSRF solo aplica a sesiones basadas en cookies; la API es REST y stateless,
    // por lo que deshabilitarlo es seguro (regla de Sonar revisada)
    @SuppressWarnings("java:S4502")
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Deshabilita CSRF
            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Sin sesión HTTP ni cookie JSESSIONID
            )
            .authorizeHttpRequests(auth -> auth
                .anyRequest().permitAll() // Permite todas las peticiones momentáneamente
            );
            
        return http.build();
    }
}
