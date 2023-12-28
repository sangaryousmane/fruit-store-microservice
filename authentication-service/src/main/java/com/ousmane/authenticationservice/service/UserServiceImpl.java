package com.ousmane.authenticationservice.service;

import com.ousmane.authenticationservice.model.Role;
import com.ousmane.authenticationservice.model.RoleType;
import com.ousmane.authenticationservice.model.User;
import com.ousmane.authenticationservice.repository.RoleRepository;
import com.ousmane.authenticationservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService{

    private final UserRepository userRepo;
    private final RoleRepository roleRepo;


    @Override
    public void saveUser(User user) {
        userRepo.save(user);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepo.findByUsername(username);
    }

    @Override
    public Boolean existsByUsername(String username) {
        return userRepo.existsByUsername(username);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return userRepo.existsByEmail(email);
    }

    @Override
    public User findById(int userId) {
        return userRepo.findById(userId);
    }

    @Override
    public Optional<Role> findByName(RoleType name) {
        return roleRepo.findByName(name);
    }
}
