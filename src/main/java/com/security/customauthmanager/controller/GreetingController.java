package com.security.customauthmanager.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
class GreetingController {

    @GetMapping("/greeting")
    public String greeting(Principal principal){
        return "greeting: " + principal.getName();
    }
}
