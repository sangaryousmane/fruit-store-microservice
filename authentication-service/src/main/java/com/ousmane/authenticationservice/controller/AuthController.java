package com.ousmane.authenticationservice.controller;
import com.ousmane.authenticationservice.exceptions.RefreshTokenException;
import com.ousmane.authenticationservice.exceptions.RoleNotFoundException;
import com.ousmane.authenticationservice.jwt.JwtUtils;
import com.ousmane.authenticationservice.model.RefreshToken;
import com.ousmane.authenticationservice.model.Role;
import com.ousmane.authenticationservice.model.RoleType;
import com.ousmane.authenticationservice.model.User;
import com.ousmane.authenticationservice.payload.request.LoginRequest;
import com.ousmane.authenticationservice.payload.request.SignUpRequest;
import com.ousmane.authenticationservice.payload.request.TokenRefreshRequest;
import com.ousmane.authenticationservice.payload.response.JwtResponse;
import com.ousmane.authenticationservice.payload.response.MessageResponse;
import com.ousmane.authenticationservice.payload.response.TokenRefreshResponse;
import com.ousmane.authenticationservice.security.CustomUserDetails;
import com.ousmane.authenticationservice.service.RefreshTokenService;
import com.ousmane.authenticationservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@RequestMapping("/authenticate")
@RestController
public class AuthController {

    private final UserService userService;
    private final RefreshTokenService refreshTokenService;

    private final AuthenticationManager authenticationManager;

    private final PasswordEncoder encoder;
    private final JwtUtils jwtUtils;

    @PostMapping("/signUp")
    public ResponseEntity<?> signUp(@RequestBody SignUpRequest request) {

        String username = request.getUsername();
        String password = request.getPassword();
        String email = request.getEmail();
        Set<Role> roles = new HashSet<>();
        Set<String> strRoles = request.getRoles();

        if (userService.existsByUsername(username))
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));

        if (userService.existsByEmail(email))
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already taken!"));

        User user = new User();
        user.setEmail(email);
        user.setUsername(username);
        user.setPassword(encoder.encode(password));

        if (strRoles != null) {
            strRoles.forEach(role -> {
                if ("ROLE_ADMIN".equals(role)) {
                    Role adminRole = null;

                    if (userService.findByName(RoleType.ROLE_ADMIN).isEmpty()) {
                        adminRole = new Role(RoleType.ROLE_ADMIN);
                    } else {
                        adminRole = userService.findByName(RoleType.ROLE_ADMIN)
                                .orElseThrow(() -> new RoleNotFoundException("Error: Admin Role is not found."));
                    }
                    roles.add(adminRole);
                } else {
                    Role userRole = null;
                    if (userService.findByName(RoleType.ROLE_USER).isEmpty()) {
                        userRole = new Role(RoleType.ROLE_USER);
                    } else {
                        userRole = userService.findByName(RoleType.ROLE_USER)
                                .orElseThrow(() -> new RoleNotFoundException("Error: User Role is not found."));
                    }
                    roles.add(userRole);
                }
            });
        } else {
            userService.findByName(RoleType.ROLE_USER).ifPresentOrElse(roles::add,
                    () -> roles.add(new Role(RoleType.ROLE_USER)));
        }
        user.setRoles(roles);
        userService.saveUser(user);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username,password);

        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        String jwt = jwtUtils.generateJwtToken(userDetails.getUsername());

        List<String> roles = userDetails.getAuthorities()
                .stream().map(GrantedAuthority::getAuthority)
                .toList();

        RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDetails.getId());

        JwtResponse jwtResponse = new JwtResponse();
        jwtResponse.setEmail(userDetails.getEmail());
        jwtResponse.setUsername(userDetails.getUsername());
        jwtResponse.setId(userDetails.getId());
        jwtResponse.setToken(jwt);
        jwtResponse.setRefreshToken(refreshToken.getToken());
        jwtResponse.setRoles(roles);

        return ResponseEntity.ok(jwtResponse);
    }

    @PostMapping("/refreshToken")
    public ResponseEntity<?> refreshtoken(@RequestBody TokenRefreshRequest request) {
        String requestRefreshToken = request.getRefreshToken();
        RefreshToken token = refreshTokenService.findByToken(requestRefreshToken)
                .orElseThrow(() -> new RefreshTokenException(requestRefreshToken + "Refresh token is not in database!"));

        RefreshToken deletedToken = refreshTokenService.verifyExpiration(token);
        User userRefreshToken = deletedToken.getUser();
        String newToken = jwtUtils.generateTokenFromUsername(userRefreshToken.getUsername());

        return ResponseEntity.ok(new TokenRefreshResponse(newToken, requestRefreshToken));
    }
}
