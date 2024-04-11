package com.redck.restaurantmsbff.service;

import com.redck.restaurantmsbff.config.JwtTokenProvider;
import com.redck.restaurantmsbff.domain.Client;
import com.redck.restaurantmsbff.logging.UserNotFoundException;
import com.redck.restaurantmsbff.logging.enumeration.LogTag;
import com.redck.restaurantmsbff.repository.ClientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
public class AuthenticationService implements AuthenticationProvider
{
    private final ClientRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    private static final Logger logger = LoggerFactory.getLogger(ClientService.class);

    @Autowired
    public AuthenticationService(final ClientRepository userRepository,
                                 final BCryptPasswordEncoder passwordEncoder)
    {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication)
    {

        String username = authentication.getName();
        String password = authentication.getCredentials().toString();


        final Optional<Client> userOptional = userRepository.findByUsername(username);
        System.out.println("USER: " + userOptional.toString());

        if(userOptional.isEmpty())
        {
            throw new UserNotFoundException("User not found");
        }

        String hashedPasswordFromDatabase = userOptional.get().getPassword();

        System.out.println("USERNAME: " + username);
        System.out.println("Password: " + password);
        System.out.println("hashed Password From Database: " + hashedPasswordFromDatabase);

        if (!passwordEncoder.matches(password, hashedPasswordFromDatabase))
        {
            throw new RuntimeException("Invalid username or password");
        }

        List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(userOptional.get().getRole())); // or "ROLE_ADMIN", depending on the user's role


        return new UsernamePasswordAuthenticationToken(userOptional, null, authorities);

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
