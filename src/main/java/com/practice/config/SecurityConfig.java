package com.practice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(AbstractHttpConfigurer::disable);
        httpSecurity.cors(cors -> cors.configurationSource(corsConfigurationSource()));
        httpSecurity.authorizeHttpRequests(request -> {
//            request.requestMatchers("/user").authenticated();
            request.anyRequest().permitAll();
//            request.requestMatchers(HttpMethod.POST, "/user").hasAnyRole("SUPER_ADMIN", "HR").
//                    requestMatchers(HttpMethod.GET, "/user").hasAnyRole("SUPER_ADMIN", "HR")
//                    .requestMatchers("/user/*").hasAnyRole("SUPER_ADMIN", "HR", "ADMIN", "USER")
//                    .requestMatchers("/user/*/*").hasAnyRole("SUPER_ADMIN", "HR")
//                    .requestMatchers("/user/asset/*/*").hasAnyRole("SUPER_ADMIN", "HR")
//                    .requestMatchers("/role").hasRole("SUPER_ADMIN");


        });
        httpSecurity.formLogin(Customizer.withDefaults());
        httpSecurity.httpBasic(Customizer.withDefaults());
        return httpSecurity.build();
    }


    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://127.0.0.1:5501"));
        configuration.setAllowedMethods(Arrays.asList("GET", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("rashi"));
        configuration.setAllowCredentials(true);
        configuration.setMaxAge((long)60000);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
