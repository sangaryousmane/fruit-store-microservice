package com.ousmane.authenticationservice.jwt.auth;

import com.ousmane.authenticationservice.entities.Roles;
import com.ousmane.authenticationservice.entities.Users;
import com.ousmane.authenticationservice.payload.RegisterRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/app")
@Tag(name = "DemoController")
public class DemoController {

    @Operation(description = "Get endpoint for manager", summary = "This is a summary for management get endpoints", responses = {@ApiResponse(description = "Success", responseCode = "200"), @ApiResponse(description = "Unauthorized / Invalid token", responseCode = "403")})
    @GetMapping("/demo-controller")
    public String defaultLoginPage() {
        return "<b> Hello, Welcome to you buy</b>";
    }


    @GetMapping("/students")
    public ResponseEntity<RegisterRequest> students(){
        RegisterRequest users=RegisterRequest.builder()
                .firstName("John")
                .lastName("Paul")
                .email("john@yahoo.com")
                .roles(Roles.USER)
                .build();
        return ResponseEntity.ok(users);
    }
}
