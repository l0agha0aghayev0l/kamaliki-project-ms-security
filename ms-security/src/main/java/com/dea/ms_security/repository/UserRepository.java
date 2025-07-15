package com.dea.ms_security.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.dea.ms_security.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    
    @EntityGraph(attributePaths = {"roles"})
    User findByUsername(String username);
    
    User findByUuid(String uuid);
}
