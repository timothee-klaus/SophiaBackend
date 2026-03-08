package com.sophia.backend.infrastructure.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter,
                         JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint,
                         JwtAccessDeniedHandler jwtAccessDeniedHandler) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
        this.jwtAccessDeniedHandler = jwtAccessDeniedHandler;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000", "http://localhost:4200", "http://localhost:8081"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH"));
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type", "Accept"));
        configuration.setExposedHeaders(Arrays.asList("Authorization"));
        configuration.setAllowCredentials(true);
        configuration.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .cors(cors -> cors.configurationSource(corsConfigurationSource()))
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .exceptionHandling(exceptions -> exceptions
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedHandler(jwtAccessDeniedHandler)
            )
            .authorizeHttpRequests(authz -> authz
                // Endpoints publics (sans authentification)
                .requestMatchers("/api/v1/auth/**").permitAll()
                .requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/swagger-ui.html").permitAll()
                .requestMatchers("/health", "/actuator/**").permitAll()
                .requestMatchers("/error").permitAll()

                // SUPER_ADMIN ONLY - Accès complet
                .requestMatchers("/api/v1/utilisateurs/**").hasRole("SUPER_ADMIN")
                .requestMatchers("/api/v1/acces-etablissement/**").hasRole("SUPER_ADMIN")
                .requestMatchers("/api/v1/logs/**").hasRole("SUPER_ADMIN")

                // SECRETAIRE & SUPER_ADMIN - Gestion établissements (CRUD)
                .requestMatchers("/api/v1/etablissements/**").hasAnyRole("SECRETAIRE", "SUPER_ADMIN")

                // SECRETAIRE & SUPER_ADMIN - Gestion cycles/niveaux (CRUD)
                .requestMatchers("/api/v1/cycles/**").hasAnyRole("SECRETAIRE", "SUPER_ADMIN")
                .requestMatchers("/api/v1/niveaux/**").hasAnyRole("SECRETAIRE", "SUPER_ADMIN")

                // SECRETAIRE & SUPER_ADMIN - Gestion frais (CRUD)
                .requestMatchers("/api/v1/frais-scolaires/**").hasAnyRole("SECRETAIRE", "SUPER_ADMIN")
                .requestMatchers("/api/v1/tranches-paiement/**").hasAnyRole("SECRETAIRE", "SUPER_ADMIN")
                .requestMatchers("/api/v1/frais-divers/**").hasAnyRole("SECRETAIRE", "SUPER_ADMIN")
                .requestMatchers("/api/v1/frais-inscription/**").hasAnyRole("SECRETAIRE", "SUPER_ADMIN")

                // SECRETAIRE & SUPER_ADMIN - Gestion années scolaires (CRUD)
                .requestMatchers("/api/v1/annees-scolaires/**").hasAnyRole("SECRETAIRE", "SUPER_ADMIN")

                // SECRETAIRE - Création/Modification | DIRECTEUR - Lecture seule | SUPER_ADMIN - Tout
                .requestMatchers("/api/v1/eleves/**").hasAnyRole("SECRETAIRE", "DIRECTEUR", "SUPER_ADMIN")
                .requestMatchers("/api/v1/inscriptions/**").hasAnyRole("SECRETAIRE", "DIRECTEUR", "SUPER_ADMIN")
                .requestMatchers("/api/v1/paiements/**").hasAnyRole("SECRETAIRE", "DIRECTEUR", "SUPER_ADMIN")
                .requestMatchers("/api/v1/blocages/**").hasAnyRole("SECRETAIRE", "DIRECTEUR", "SUPER_ADMIN")
                .requestMatchers("/api/v1/documents/**").hasAnyRole("SECRETAIRE", "DIRECTEUR", "SUPER_ADMIN")
                .requestMatchers("/api/v1/notifications/**").hasAnyRole("SECRETAIRE", "DIRECTEUR", "SUPER_ADMIN")
                .requestMatchers("/api/v1/recus/**").hasAnyRole("SECRETAIRE", "DIRECTEUR", "SUPER_ADMIN")

                // Tous les autres requêtes nécessitent une authentification
                .anyRequest().authenticated()
            )
            .httpBasic(basic -> basic.disable());

        // Ajouter le filtre JWT avant le filtre d'authentification de Spring
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}


