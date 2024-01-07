package com.ous.auth2.sociallogin.jwt.repo;

import com.ous.auth2.sociallogin.jwt.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {

    @Query("SELECT u FROM Users u WHERE u.email=:email")
    Optional<Users> findByEmail(@Param("email") String email);
}
