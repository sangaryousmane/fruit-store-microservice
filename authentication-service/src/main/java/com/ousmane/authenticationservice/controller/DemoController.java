package com.ousmane.authenticationservice.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/secured/endpoint")
public class DemoController {


    @Secured("hasRole(ROLE_ADMIN)")
    @GetMapping("/admin")
    public String adminOnly(){
        return "Hello from admin endpoint";
    }

    @Secured("hasRole(ROLE_USER)")
    @GetMapping("/user")
    public String userOnly(){
        return "Hello from user only endpoint";
    }

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @GetMapping("/user_admin")
    public String userAdmin(){
        return "I am from user and admin";
    }
}
