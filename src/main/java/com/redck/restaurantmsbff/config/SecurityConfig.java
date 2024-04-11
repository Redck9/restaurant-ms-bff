package com.redck.restaurantmsbff.config;

import com.redck.restaurantmsbff.service.AuthenticationService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
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
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        logger.info("Username: {}", username);

        logger.info("Configuring security...");
        http
           .csrf(AbstractHttpConfigurer::disable)
           .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers("/", "/login","/index","/index2","/error", "/api/login", "/api/register", "/resources/**").permitAll()
                        .anyRequest().authenticated()
                )
                //.formLogin(withDefaults())
                .formLogin((form) -> form
                        .loginPage("/login").permitAll()
                        .loginProcessingUrl("/api/login")
                        .defaultSuccessUrl("/")
                        //.successHandler(successHandler())
                )
                .logout(LogoutConfigurer::permitAll);
                /*.logout(logout -> logout
                        .addLogoutHandler(logoutHandler()));*/
                /*.csrf(csrf -> csrf.disable());;*/
                //.httpBasic(Customizer.withDefaults())
                //.sessionManagement(httpSecuritySessionManagementConfigurer -> httpSecuritySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        logger.info("Security configuration completed.");
        return http.build();
    }

    private LogoutHandler logoutHandler() {
        return (request, response, authentication) -> {
            try
            {
                response.sendRedirect("/home");
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