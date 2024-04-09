package com.redck.restaurantmsbff.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.util.Map;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private static final Logger logger = LoggerFactory.getLogger(SecurityConfig.class);


    @Value("${com.redck.username}")
    private String username;

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        logger.info("Username: {}", username);
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/", "/login", "/home").permitAll()
                        //.anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/")
                        .successHandler(successHandler())
                        .permitAll()
                )
                .logout(logout -> logout
                        .addLogoutHandler(logoutHandler()));
        return http.build();
    }

    @Bean
    public AuthenticationSuccessHandler successHandler() {
        return new SimpleUrlAuthenticationSuccessHandler() {
            @Override
            public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                clearAuthenticationAttributes(request);
                response.sendRedirect("/home"); // Redirect to the home page after successful authentication
            }
        };
    }

    private LogoutHandler logoutHandler() {
        return (request, response, authentication) -> {
            try
            {
                response.sendRedirect("/");
            }
            catch (IOException e)
            {
                throw new RuntimeException(e);
            }
        };
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}