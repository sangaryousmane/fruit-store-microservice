package com.ousmane.authenticationservice.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.Instant;

@Entity(name = "RefreshToken")
@Table(name = "refresh_tokens",
        uniqueConstraints = @UniqueConstraint(name = "unique_token_key",
                columnNames = "token"))
@Data
public class RefreshToken extends BaseEntity implements Serializable {

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Column(name = "token", unique = true)
    private String token;

    @Column(name = "expiryDate", nullable = false)
    private Instant expiryDate;
}
