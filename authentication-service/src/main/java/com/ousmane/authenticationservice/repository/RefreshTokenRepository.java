package com.ousmane.authenticationservice.repository;
import com.ousmane.authenticationservice.model.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Integer> {


    @Query("SELECT r FROM RefreshToken r WHERE r.token=:token")
    Optional<RefreshToken> findByToken(@Param("token") String token);
}
