package com.clinicservice.MultimediaServer.Security;

import java.util.Arrays;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@Configuration
@EnableAutoConfiguration
@EnableWebSecurity
public class SpringSecurityConfig{

    private final JwtTokenProvider jwtTokenProvider;

    public SpringSecurityConfig(JwtTokenProvider jwtTokenProvider) {
      this.jwtTokenProvider = jwtTokenProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
            .requestMatchers("/ws/**").permitAll()
                .anyRequest().authenticated()
            )
            .addFilterBefore(new JwtAuthorizationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class)
            .csrf(csrf -> csrf
                .ignoringRequestMatchers("/ws/**")
                .disable())
            .cors(cors->cors.configurationSource(corsConfigurationSource()))
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return http.build();
    }
  
    @Bean
    CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(Arrays.asList("https://medicfile.net"));
        config.setAllowedOriginPatterns(Arrays.asList("*"));
        config.setAllowedMethods(Arrays.asList("GET", "POST", "DELETE", "PUT"));
        config.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
    
    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        return objectMapper;
    }
    
}
