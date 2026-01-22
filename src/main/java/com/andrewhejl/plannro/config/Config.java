package com.andrewhejl.plannro.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class Config {

    @Bean
    public PasswordEncoder passwordEncoder() { return new BCryptPasswordEncoder(); }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception
    {
        http
        .csrf().disable()       //disable csrf
        .authorizeHttpRequests(auth->auth.anyRequest().permitAll()) //allow any req
        .httpBasic().disable()  //disable http
        .formLogin().disable(); //disable form
        return http.build();
    }
}
