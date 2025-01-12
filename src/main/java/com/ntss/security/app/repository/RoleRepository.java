package com.ntss.security.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ntss.security.app.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);  // Retrieve a role by its name (e.g. "ROLE_USER")
    
}
