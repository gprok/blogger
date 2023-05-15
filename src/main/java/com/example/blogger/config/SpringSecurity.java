package com.example.blogger.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SpringSecurity {

    @Bean
    public static PasswordEncoder passwordEncoder() {
        // No encryption, later replace with BCryptPasswordEncoder
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeHttpRequests(
                        (authorize) -> authorize
                                .requestMatchers(("/user/**")).hasRole("BLOGGER")
                                .requestMatchers("/**").permitAll()
                )
                .formLogin(
                        form -> form.loginPage("/login")
                                .loginProcessingUrl("/login")
                                .defaultSuccessUrl("/user/profile")
                                .permitAll()
                )
                .logout(
                        logout -> logout.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                                .permitAll()
                );

        return http.build();
    }

}
