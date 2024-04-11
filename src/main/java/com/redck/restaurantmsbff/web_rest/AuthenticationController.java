package com.redck.restaurantmsbff.web_rest;

import com.redck.restaurantmsbff.config.JwtTokenProvider;
import com.redck.restaurantmsbff.logging.UserNotFoundException;
import com.redck.restaurantmsbff.service.AuthenticationService;
import com.redck.restaurantmsbff.service.ClientService;
import com.redck.restaurantmsbff.service.mapper.ClientMapper;
import com.redck.restaurantmsbff.service.model.ClientDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
public class AuthenticationController {

    @GetMapping("/login")
    String login() {
        return "index";
    }

    @GetMapping("/")
    String authenticated()
    {
        return "index2";
    }

}
