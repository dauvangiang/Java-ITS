package com.dvgiang.electricitybillingsystem.config;

import com.dvgiang.electricitybillingsystem.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class AppConfig {
  private final UserRepository userRepository;

  @Bean
  public AuthenticationProvider authenticationProvider(PasswordEncoder passwordEncoder) {
    DaoAuthenticationProvider authenProvider = new DaoAuthenticationProvider();
    authenProvider.setUserDetailsService(userDetailsService());
    authenProvider.setPasswordEncoder(passwordEncoder);
    return authenProvider;
  }

  @Bean
  public UserDetailsService userDetailsService() {
    return username -> userRepository.getUserByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("User not found!"));
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration authenConfiguration) throws Exception {
    return authenConfiguration.getAuthenticationManager();
  }
}
