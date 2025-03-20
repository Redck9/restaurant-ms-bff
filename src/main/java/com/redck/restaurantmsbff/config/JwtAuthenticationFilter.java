package com.redck.restaurantmsbff.config;

import com.redck.restaurantmsbff.domain.Client;
import com.redck.restaurantmsbff.service.ClientService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter
{
    private final JwtTokenProvider jwtTokenProvider;
    private final ClientService clientService;

    public JwtAuthenticationFilter(@NonNull JwtTokenProvider jwtTokenProvider, @NonNull ClientService clientService)
    {
        this.jwtTokenProvider = jwtTokenProvider;
        this.clientService = clientService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException
    {

        if (request.getRequestURI().startsWith("/api/login") || request.getRequestURI().startsWith("/api/register")) {
            filterChain.doFilter(request, response); // Continue the filter chain
            return;
        }

        String token = jwtTokenProvider.getTokenFromRequest(request);
        System.out.println("Extracted Token: " + token);

        if(token != null && jwtTokenProvider.validateToken(token))
        {
            String userUid = jwtTokenProvider.getUserUidFromToken(token);
            Client client = clientService.getUser(userUid);
            System.out.println("Client fetched: " + client);
            if (client != null) {
                UsernamePasswordAuthenticationToken authentication = getUsernamePasswordAuthenticationToken(client);

                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                System.out.println("Setting authentication: " + authentication);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
            else
            {
                System.out.println("Client not found for userUid: " + userUid);
            }


        }
        else
        {
            System.out.println("Invalid or missing token");
        }

        filterChain.doFilter(request, response);
    }

    private static UsernamePasswordAuthenticationToken getUsernamePasswordAuthenticationToken(Client client) {
        System.out.println("Client Role: " + client.getRole());
        List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(client.getRole()));

        // Convert Client to UserDetails manually
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                client.getUsername(),
                client.getPassword(),
                authorities  // Assuming Client implements UserDetails or has a method for roles
        );

        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }
}
