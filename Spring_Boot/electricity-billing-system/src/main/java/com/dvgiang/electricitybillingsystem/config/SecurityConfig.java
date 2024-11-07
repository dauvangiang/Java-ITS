package com.dvgiang.electricitybillingsystem.config;

import com.dvgiang.electricitybillingsystem.dto.response.BaseResponse;
import com.dvgiang.electricitybillingsystem.filter.JwtAuthenFilter;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
@EnableMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig {
    private final JwtAuthenFilter jwtAuthenFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request -> request
                                .requestMatchers("/auth/**", "/visitor/**").permitAll()
                                .anyRequest().authenticated()
                )
                .exceptionHandling(e -> e
//                .authenticationEntryPoint((request, response, authException) -> {
//                    response.setStatus(401);
//                    response.getWriter().write("Please provide token!");
//                })
                                .accessDeniedHandler((request, response, accessDeniedException) -> {
                                    response.setStatus(403);
                                    response.setContentType("application/json");

                                    String jsonResponse = (new ObjectMapper()).writeValueAsString(
                                            BaseResponse.fail("Access is not allowed!")
                                    );
                                    response.getWriter().write(jsonResponse);
                                })
                )
                .sessionManagement(sess -> sess
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
