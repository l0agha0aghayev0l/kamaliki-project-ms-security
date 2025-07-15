package com.dea.ms_security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dea.ms_security.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    
    Role findByName(String name);
}
