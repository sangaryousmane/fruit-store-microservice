package com.ousmane.authenticationservice.jwt.auth;

import com.ousmane.authenticationservice.payload.AuthenticationRequest;
import com.ousmane.authenticationservice.payload.AuthenticationResponse;
import com.ousmane.authenticationservice.payload.RegisterRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest register){
        return ResponseEntity.ok(authenticationService.registerUser(register));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticateUser(
            @RequestBody AuthenticationRequest authResponse){
        return ResponseEntity.ok(authenticationService.authenticateUser(authResponse));
    }


}
