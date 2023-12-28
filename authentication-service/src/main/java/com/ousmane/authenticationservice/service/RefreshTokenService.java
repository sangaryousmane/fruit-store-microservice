package com.ousmane.authenticationservice.service;

import com.ousmane.authenticationservice.model.RefreshToken;
import com.ousmane.authenticationservice.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepo;
    private final UserService userService;

    @Value("${jwt.refrEshexpireMs}")
    private Long refreshTokenDuration;

    public RefreshToken createRefreshToken(int userId){
        return  RefreshToken.builder()
                .user(userService.findById(userId))
                .expiryDate(Instant.now().plusMillis(refreshTokenDuration))
                .token(UUID.randomUUID().toString())
                .build();
    }

    public Optional<RefreshToken> findByToken(String token){
        return refreshTokenRepo.findByToken(token);
    }

    public RefreshToken verifyExpiration(RefreshToken token){
       if (token.getExpiryDate().compareTo(Instant.now()) < 0)
                refreshTokenRepo.delete(token);
       return token;
    }
}
