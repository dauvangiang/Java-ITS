package com.dvgiang.electricitybillingsystem.security;

import com.dvgiang.electricitybillingsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    //Dùng trong bản cũ
//    @Autowired
//    UserService userService;

    //Step 3: PasswordEncoder: BCryptPasswordEncoder được cấu hình sẽ được sử dụng để so khớp mật khẩu mà người dùng nhập vào với mật khẩu đã mã hóa trong DB
    @Bean
    public PasswordEncoder passwordEncoder() {
        //Sử dụng BCrypt để mã hóa mật khẩu
        return new BCryptPasswordEncoder(); //SO sánh 2 mật khẩu
    }

    //Cách cũ: WebSecurityConfigurerAdapter
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
//    }

    //Cách mới
    // Step 2.1: AuthenticationManager lấy thông tin đăng nhập và gửi đến AuthenticationProvider
    // Step 2.2: AuthenticationProvider gọi loadUserByUsername của UserService (triển khai từ UserDetailsService)
            //: UserService gọi đến phương thức tương ứng trong UserRepository để truy vấn từ csdl => user
    // Step 2.3: Spring Security dùng CustomUserDetails để lấy thông tin về tên người dùng, mật khẩu đã mã hóa và các quyền hạn (roles).

    //Không cần thiết, Spring Boot tự động cấu hình
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
//        return authenticationConfiguration.getAuthenticationManager();
//    }

    //Step 4: Nếu xác thực thành công, Spring Security sẽ cấp quyền cho người dùng dựa trên quyền hạn (roles)
    //         mà bạn đã định nghĩa trong CustomUserDetails (ví dụ: ROLE_ADMIN, ROLE_USER).

    // Step 5: điều hướng

    // Step 1: Chặn người dùng khi nhấn đăng nhập thông qua SecurityFilterChain, /login là mặc định
    @Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers("/configurations/**", "/customers/**").hasRole("ADMIN")
                        .requestMatchers("/histories/**").hasRole("USER")
                        .anyRequest().authenticated()
                )
                .formLogin(formLogin -> formLogin
                        .permitAll()
                        .defaultSuccessUrl("/")
                )
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }

//    @Bean
//    public UserDetailsService userDetailsService() {
//        UserDetails admin = User.builder()
//                .username("admin")
//                .password("{noop}password") // Sử dụng {noop} để không mã hóa mật khẩu (chỉ cho mục đích thử nghiệm)
//                .roles("ADMIN")
//                .build();
//
//        UserDetails user = User.builder()
//                .username("user")
//                .password("{noop}password") // Sử dụng {noop} để không mã hóa mật khẩu (chỉ cho mục đích thử nghiệm)
//                .roles("USER")
//                .build();
//
//        return new InMemoryUserDetailsManager(admin, user);
//    }
}
