package org.atalibdev.getway.controller;

import jakarta.annotation.security.RolesAllowed;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/demo")
public class DemoController {


    @GetMapping("/hello")
    @RolesAllowed({"client_user", "client_admin"})
    public String helloSave() {
        return "Hello keycloak and & Spring Boot";

    }

    @GetMapping("/hello2")
    @PreAuthorize("hasRole('client_admin')")
    public String helloSecured() {
        return "Hello secured endpoint & ADMIN";
    }
}
