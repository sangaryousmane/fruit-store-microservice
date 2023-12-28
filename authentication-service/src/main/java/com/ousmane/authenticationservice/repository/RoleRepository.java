package com.ousmane.authenticationservice.repository;

import com.ousmane.authenticationservice.model.Role;
import com.ousmane.authenticationservice.model.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    @Query("SELECT r FROM Role r WHERE r.name=:name")
    Optional<Role> findByName(@Param("name") RoleType name);
}
