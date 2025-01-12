package com.ntss.security.app.model;

import org.springframework.security.core.GrantedAuthority;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; // Role name (e.g., "ROLE_USER", "ROLE_ADMIN")

    @Override
    public String getAuthority() {
        return name;
    }

    // Other getters and setters
}
