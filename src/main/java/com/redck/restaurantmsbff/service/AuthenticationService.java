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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;

@Service
public class AuthenticationService
{
    private final ClientRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    private final JwtTokenProvider jwtTokenProvider;

    private static final Logger logger = LoggerFactory.getLogger(ClientService.class);

    @Autowired
    public AuthenticationService(final ClientRepository userRepository,
                                 final BCryptPasswordEncoder passwordEncoder,
                                 final JwtTokenProvider jwtTokenProvider)
    {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public String authenticate(final String username, final String password)
    {
        final Optional<Client> userOptional = userRepository.findByUsername(username);

        if(userOptional.isEmpty())
        {
            throw new UserNotFoundException("User not found");
        }

        String hashedPasswordFromDatabase = userOptional.get().getPassword();

        String hashedPasswordFromInput = passwordEncoder.encode(password);

        if (!passwordEncoder.matches(hashedPasswordFromInput, hashedPasswordFromDatabase))
        {
            throw new RuntimeException("Invalid username or password");
        }

        return jwtTokenProvider.generateToken(username);
    }
}
