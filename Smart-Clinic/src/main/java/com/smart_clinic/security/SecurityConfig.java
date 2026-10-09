package com.smart_clinic.security;

import com.smart_clinic.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtFilter jwtFilter;
    private final UserRepository userRepository; // ← أضيفي

    public SecurityConfig(JwtFilter jwtFilter ,
                          UserRepository userRepository) {
        this.jwtFilter = jwtFilter;
        this.userRepository = userRepository;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http)
            throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authenticationProvider(authenticationProvider()) // ← أضيفي
                .authorizeHttpRequests(auth -> auth

                        .requestMatchers("/api/auth/**").permitAll()


                        .requestMatchers("/api/admin/**").hasAuthority("ADMIN")


                        .requestMatchers("/api/doctor/**").hasAuthority("DOCTOR")


                        .requestMatchers("/api/patients/**").hasAuthority("PATIENT")


                        .anyRequest().authenticated()
                )
            .sessionManagement(session -> session
                .sessionCreationPolicy(
                        SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtFilter,
                        UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider =
                new DaoAuthenticationProvider();
        provider.setUserDetailsService(
                username -> userRepository
                        .findByEmail(username)
                        .map(user -> org.springframework.security.core.userdetails.User
                                .withUsername(user.getEmail())
                                .password(user.getPassword())
                                .authorities(user.getRole())
                                .build())
                        .orElseThrow(() ->
                                new UsernameNotFoundException("User not found"))
        );
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }
    ///////////////////////////////////////////////////////////////////////////////////////
}