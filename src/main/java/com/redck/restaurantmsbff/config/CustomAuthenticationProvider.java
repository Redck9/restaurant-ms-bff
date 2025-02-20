package com.redck.restaurantmsbff.config;

import com.redck.restaurantmsbff.domain.Client;
import com.redck.restaurantmsbff.logging.UserNotFoundException;
import com.redck.restaurantmsbff.repository.ClientRepository;
import com.redck.restaurantmsbff.service.ClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider
{
    private final ClientRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    //private final PasswordEncoder passwordEncoder;

    private static final Logger logger = LoggerFactory.getLogger(ClientService.class);

    @Autowired
    public CustomAuthenticationProvider(final ClientRepository userRepository,
                                        final BCryptPasswordEncoder passwordEncoder)
    {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException
    {

        String username = authentication.getName();
        String password = authentication.getCredentials().toString();


        Optional<Client> userOptional = userRepository.findByUsername(username);
        System.out.println("USER: " + userOptional.toString());

        if(userOptional.isEmpty())
        {
            userOptional = userRepository.findByEmail(username);
        }

        if(userOptional.isEmpty())
        {
            throw new UserNotFoundException("User not found");
        }

        String hashedPasswordFromDatabase = userOptional.get().getPassword();

        System.out.println("🔹 Retrieved User: " + username);
        System.out.println("🔹 Retrieved Hashed Password: " + hashedPasswordFromDatabase);
        System.out.println("🔹 Does Password Match? " + passwordEncoder.matches(password, hashedPasswordFromDatabase));


        if (!passwordEncoder.matches(password, hashedPasswordFromDatabase))
        {
            throw new BadCredentialsException("Invalid username or password");
        }

        String role = userOptional.get().getRole();
        List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(role)); // or "ROLE_ADMIN", depending on the user's role

        logger.info("Authorities: {}!", authorities);

        return new UsernamePasswordAuthenticationToken(userOptional.get(), hashedPasswordFromDatabase, authorities);

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
