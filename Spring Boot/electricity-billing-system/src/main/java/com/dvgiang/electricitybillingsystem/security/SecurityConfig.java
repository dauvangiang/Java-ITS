package com.dvgiang.electricitybillingsystem.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(csrf -> csrf.disable()) //Tắt xác thực
                .authorizeHttpRequests((requests) -> requests
                        .anyRequest().permitAll()
                );
        return httpSecurity.build();
    }
}

/* *
* requestMatchers(): chỉ định url và quy tắc bảo mật cụ thể, ví dụ: .requestMatchers("/", "/home", "/public/**") //( = antMatchers() trong các phiên bản spring security thấp hơn)
*
*
* authenticated(): bất kỳ endpoint nào không được khai báo cụ thể cũng cần được xác thực
* permitAll(): Ngược lại với authenticated()
* hasRole(String role): Role cụ thể mưới được truy cập, ví dụ hasRole("ADMIN")
*
*
*
*
* */
