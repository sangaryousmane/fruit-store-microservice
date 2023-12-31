/**
 * This class registers a new user and provide them with a token
 * afterwards.
 */
package com.ousmane.authenticationservice.payload;

import com.ousmane.authenticationservice.entities.Roles;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Roles roles;
}
