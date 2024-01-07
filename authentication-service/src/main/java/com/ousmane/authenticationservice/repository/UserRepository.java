package com.ousmane.authenticationservice.repository;

import com.ousmane.authenticationservice.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {

    @Query("SELECT u FROM Users u WHERE u.email=:email")
    Optional<Users> findByEmail(@Param("email") String email);

    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN TRUE ELSE FALSE END" +
            " FROM Users u WHERE u.email=:email")
    Boolean existsByEmail(@Param("email") String email);

    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN TRUE ELSE FALSE END " +
            "FROM Users u WHERE u.email=:email")
    Boolean existsByUsername(@Param("email") String email);

    @Query("SELECT u FROM Users u WHERE u.id=:id")
    Users findById(@Param("id") int userId);
}
