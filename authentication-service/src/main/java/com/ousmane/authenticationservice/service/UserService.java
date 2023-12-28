package com.ousmane.authenticationservice.service;

import com.ousmane.authenticationservice.model.Role;
import com.ousmane.authenticationservice.model.RoleType;
import com.ousmane.authenticationservice.model.User;

import java.util.Optional;

public interface UserService {

    void saveUser(User user);
    Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
    User findById(int userId);
    Optional<Role> findByName(RoleType name);
}
