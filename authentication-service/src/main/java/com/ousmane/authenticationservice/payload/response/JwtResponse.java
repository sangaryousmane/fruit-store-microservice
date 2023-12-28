package com.ousmane.authenticationservice.payload.response;
import lombok.Data;
import java.util.List;

@Data
public class JwtResponse {

    private String token;
    private String type = "Bearer";
    private String refreshToken;
    private int id;
    private String username;
    private String email;
    private List<String> roles;
}
