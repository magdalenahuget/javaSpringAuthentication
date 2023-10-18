package com.company.authentication.repository;

import com.company.authentication.model.ERole;
import com.company.authentication.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(ERole name);
}