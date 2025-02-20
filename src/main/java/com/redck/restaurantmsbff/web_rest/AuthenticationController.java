package com.redck.restaurantmsbff.web_rest;

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
